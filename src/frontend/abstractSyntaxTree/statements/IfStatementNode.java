package frontend.abstractSyntaxTree.statements;


import backend.AssemblyGenerator;
import backend.Condition;
import backend.Register;
import backend.instructions.BranchInstruction;
import backend.instructions.CompareInstruction;
import backend.instructions.Instruction;
import frontend.abstractSyntaxTree.expressions.ExpressionNode;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IfStatementNode extends StatementNode {

  private final ExpressionNode condition;
  private final StatementNode trueBranch;
  private final StatementNode falseBranch;

  public IfStatementNode(ExpressionNode condition, StatementNode trueBranch,
                         StatementNode falseBranch) {
    this.condition = condition;
    this.trueBranch = trueBranch;
    this.falseBranch = falseBranch;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    condition.semanticCheck(symbolTable, errorList);

    Type conditionType = condition.getType(symbolTable);
    if (conditionType == null) {
      errorList.addError(new SemanticError("Condition provided does not have a valid type"));
    } else if (!conditionType.equals(new BaseTypes(BaseTypes.base_types.BOOL))) {
      errorList.addError(new SemanticError("Condition provided is not a boolean."));
    }

    SymbolTable trueBranchTable = symbolTable.newChild(trueBranch);
    trueBranch.semanticCheck(trueBranchTable, errorList);

    SymbolTable falseBranchTable = symbolTable.newChild(falseBranch);
    falseBranch.semanticCheck(falseBranchTable, errorList);
  }

  @Override
  public List<Instruction> generateAssembly(AssemblyGenerator generator,
                                            SymbolTable symbolTable,
                                            Stack<Register.ID> available) {
    List<Instruction> instructions = new ArrayList<>();

    instructions.addAll(condition.generateAssembly(generator,
            symbolTable, available));
    instructions.add(new CompareInstruction(
            generator.getRegister(available.peek()), 0));
    String falseLabel = generator.generateIfBranch(
            falseBranch.generateAssembly(generator,
            symbolTable.getChild(falseBranch), available));
    instructions.add(new BranchInstruction(Condition.EQ, falseLabel));
    instructions.addAll(trueBranch.generateAssembly(generator,
            symbolTable.getChild(trueBranch), available));
    String endLabel = generator.generateIfDeallocSP(symbolTable, falseLabel);
    instructions.add(new BranchInstruction(new ArrayList<>(), endLabel));

    return instructions;
  }

  @Override
  public boolean containsReturn() {
    return trueBranch.containsReturn() || falseBranch.containsReturn();
  }

  @Override
  public void matchReturnType(Type type) {
    trueBranch.matchReturnType(type);
    falseBranch.matchReturnType(type);
  }

  @Override
  public boolean endsWithReturn() {
    return trueBranch.endsWithReturn() && falseBranch.endsWithReturn();
  }

  @Override
  public boolean containsExit() {
    return trueBranch.containsExit() || falseBranch.containsExit();
  }
}

package frontend.abstractSyntaxTree.statements;

import backend.AssemblyGenerator;
import backend.Condition;
import backend.Register;
import backend.instructions.BranchInstruction;
import backend.instructions.CompareInstruction;
import backend.instructions.LabelInstruction;
import frontend.abstractSyntaxTree.expressions.ExpressionNode;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

import java.util.ArrayList;
import java.util.Stack;

public class WhileStatementNode extends StatementNode {

  private final ExpressionNode condition;
  private final StatementNode doStatement;

  public WhileStatementNode(ExpressionNode condition, StatementNode
          doStatement) {
    this.condition = condition;
    this.doStatement = doStatement;
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

    SymbolTable doStatementTable = symbolTable.newChild(doStatement);
    doStatement.semanticCheck(doStatementTable, errorList);
  }

  @Override
  public void generateAssembly(AssemblyGenerator generator,
                                            SymbolTable symbolTable,
                                            Stack<Register.ID> available) {
    String label = generator.generateNewLabel();
    String trueLabel = generator.generateNewLabel();

    generator.putAfterActiveLabel(label);
    generator.putAfterActiveLabel(trueLabel);

    generator.addInstruction(new BranchInstruction(new ArrayList<>(), label));
    generator.setActiveLabel(label);
    generator.addInstruction(new LabelInstruction(label));
    condition.generateAssembly(generator, symbolTable, available);
    generator.addInstruction(new CompareInstruction(
            generator.getRegister(available.peek()), 1));
    generator.addInstruction(new BranchInstruction(Condition.EQ, trueLabel));
    generator.setActiveLabel(trueLabel);
    generator.addInstruction(new LabelInstruction(trueLabel));
    doStatement.generateAssembly(generator, symbolTable.getChild(doStatement),
            available);
    generator.sortLabels(trueLabel, label);
    generator.setActiveLabel(label);
  }

  @Override
  public boolean containsReturn() {
    return doStatement.containsReturn();
  }

  @Override
  public void matchReturnType(Type type) {
    doStatement.matchReturnType(type);
  }

  @Override
  public boolean endsWithReturn() {
    return doStatement.endsWithReturn();
  }

  @Override
  public boolean containsExit() {
    return doStatement.containsExit();
  }

  @Override
  public String toString() {
    return "while " + condition.toString() + " do\n" +
            doStatement.toString() + "\ndone";
  }
}

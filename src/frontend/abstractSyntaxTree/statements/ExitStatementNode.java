package frontend.abstractSyntaxTree.statements;


import backend.AssemblyGeneratorVisitor;
import backend.Condition;
import backend.Register;
import backend.instructions.BranchInstruction;
import backend.instructions.Instruction;
import backend.instructions.MovInstruction;
import frontend.abstractSyntaxTree.expressions.ExpressionNode;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

import java.util.ArrayList;
import java.util.List;

public class ExitStatementNode extends StatementNode {
  private final ExpressionNode exitCode;

  public ExitStatementNode(ExpressionNode exitCode) {
    this.exitCode = exitCode;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {

    exitCode.semanticCheck(symbolTable, errorList);

    Type resultType = exitCode.getType(symbolTable);
    if (resultType == null || !resultType.equals(new BaseTypes(BaseTypes.base_types.INT))) {
      errorList.addError(new SemanticError("Can't exit program with non-integer value."));
    }
  }

  @Override
  public List<Instruction> generateAssembly(AssemblyGeneratorVisitor assemblyGeneratorVisitor, SymbolTable symbolTable) {
    List<Instruction> instructions = new ArrayList<>();

    Register R0 = assemblyGeneratorVisitor.getRegister(Register.ID.R0);
    Register R4 = assemblyGeneratorVisitor.getRegister(Register.ID.R4);

    // {reg} is first available gp reg (for now using r4)
    // LDR {reg}, -1
    instructions.addAll(
            exitCode.generateAssembly(assemblyGeneratorVisitor, symbolTable));
    instructions.add(new BranchInstruction(Condition.L, "exit"));

    // MOV r0, {reg}
    instructions.add(new MovInstruction(R0, R4));

    return instructions;
  }

  @Override
  public boolean endsWithReturn() {
    return true;
  }

  @Override
  public boolean containsExit() {
    return true;
  }
}

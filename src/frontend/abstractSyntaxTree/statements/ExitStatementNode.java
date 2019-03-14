package frontend.abstractSyntaxTree.statements;


import backend.AssemblyGenerator;
import backend.Condition;
import backend.Register;
import backend.instructions.BranchInstruction;
import backend.instructions.MovInstruction;
import frontend.abstractSyntaxTree.expressions.ExpressionNode;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;
import shell.Heap;
import shell.ShellStatementControl;

import java.util.Stack;

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
  public void generateAssembly(
          AssemblyGenerator generator,
          SymbolTable symbolTable, Stack<Register.ID> available) {
    Register R0 = generator.getRegister(Register.ID.R0);
    Register nextAvail = generator.getRegister(available.peek());

    // {reg} is first available gp reg (for now using r4)
    // LDR {reg}, -1
    exitCode.generateAssembly(generator, symbolTable, available);

    // MOV r0, {reg}
    generator.addInstruction(new MovInstruction(R0, nextAvail));
    generator.addInstruction(new BranchInstruction(Condition.L, "exit"));
  }

  @Override
  public boolean endsWithReturn() {
    return true;
  }

  @Override
  public boolean containsExit() {
    return true;
  }

  @Override
  public ShellStatementControl applyStatement(SymbolTable symbolTable,
      Heap heap) {
    return ShellStatementControl.exit();
  }

  @Override
  public String toString() {
    return "exit " + exitCode.toString();
  }
}

package frontend.abstractSyntaxTree.statements;

import backend.AssemblyGenerator;
import backend.Register;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import shell.Heap;
import shell.ShellStatementControl;

import java.util.Stack;

public class SkipStatementNode extends StatementNode {

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
  }

  @Override
  public void generateAssembly(AssemblyGenerator generator,
                               SymbolTable symbolTable,
                               Stack<Register.ID> available) {
  }

  @Override
  public String toString() {
    return "skip";
  }

  @Override
  public ShellStatementControl applyStatement(SymbolTable symbolTable,
      Heap heap) {
    return ShellStatementControl.cont();
  }
}

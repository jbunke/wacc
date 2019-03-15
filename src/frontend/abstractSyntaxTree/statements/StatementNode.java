package frontend.abstractSyntaxTree.statements;


import frontend.abstractSyntaxTree.Node;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;
import shell.structural.ShellStatementControl;
import shell.structural.Heap;

public abstract class StatementNode implements Node {
  public boolean endsWithReturn() {
    return false;
  }

  public boolean containsReturn() {
    return false;
  }

  public boolean containsExit() {
    return false;
  }

  public void matchReturnType(Type type) {
  }

  public abstract ShellStatementControl applyStatement(SymbolTable symbolTable,
      Heap heap);
}

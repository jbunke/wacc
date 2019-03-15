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

  /**
   * All StatementNodes override this method for statement execution from shell
   *
   * @param symbolTable SymbolTable in this scope for access to functions and
   *                    variables
   * @param heap Heap from shell used for memory allocation for pairs and
   *             arrays
   *
   * @return Usually cont() from ShellStatementNode, but a structure telling
   * the caller to return a specific Object or cease executing statements in
   * the case that StatementNode happens to be ExitStatementNode or
   * ReturnStatementNode
   * */
  public abstract ShellStatementControl applyStatement(SymbolTable symbolTable,
      Heap heap);
}

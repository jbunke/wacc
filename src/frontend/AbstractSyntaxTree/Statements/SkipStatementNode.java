package frontend.AbstractSyntaxTree.Statements;

import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;

public class SkipStatementNode extends StatementNode {

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
  }
}

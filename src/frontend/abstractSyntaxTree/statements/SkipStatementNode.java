package frontend.abstractSyntaxTree.statements;

import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;

public class SkipStatementNode extends StatementNode {

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
  }
}

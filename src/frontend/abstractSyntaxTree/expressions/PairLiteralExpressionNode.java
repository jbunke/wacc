package frontend.abstractSyntaxTree.expressions;


import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Pair;
import frontend.symbolTable.types.Type;

public class PairLiteralExpressionNode extends ExpressionNode {
  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    return new Pair(null, null);
  }
}

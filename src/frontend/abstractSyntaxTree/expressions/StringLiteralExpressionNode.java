package frontend.abstractSyntaxTree.expressions;

import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Array;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

public class StringLiteralExpressionNode extends ExpressionNode {
  private final String value;

  public StringLiteralExpressionNode(String v) {
    value = v;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    return new Array(new BaseTypes(BaseTypes.base_types.CHAR));
  }
}

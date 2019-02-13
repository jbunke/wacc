package frontend.abstractSyntaxTree.statements;

import frontend.abstractSyntaxTree.expressions.ExpressionNode;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Array;
import frontend.symbolTable.types.Pair;
import frontend.symbolTable.types.Type;

public class FreeStatementNode extends StatementNode {
  private final ExpressionNode expression;

  public FreeStatementNode(ExpressionNode expression) {
    this.expression = expression;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    expression.semanticCheck(symbolTable, errorList);

    Type exprType = expression.getType(symbolTable);
    if (exprType == null || (!(exprType instanceof Array) && !(exprType instanceof Pair))) {
      errorList.addError(new SemanticError("'free' call expected type: Array or Pair, but given type: " + exprType.toString()));
    }
  }
}

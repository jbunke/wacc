package frontend.AbstractSyntaxTree.Statements;

import frontend.AbstractSyntaxTree.Expressions.ExpressionNode;
import frontend.SymbolTable.SemanticError;
import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.Type;

public class ReturnStatementNode extends StatementNode {
  private final ExpressionNode result;
  private Type returnType = null;

  public ReturnStatementNode(ExpressionNode result) {
    this.result = result;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    result.semanticCheck(symbolTable, errorList);

    if (returnType != null) {
      Type resultType = result.getType(symbolTable);
      if (resultType == null || !returnType.equals(resultType)) {
        errorList.addError(new SemanticError(
            "Return type does not match that specified in function declaration."));
      }
    }
  }

  @Override
  public boolean containsReturn() {
    return true;
  }

  //all calls to this function in Statement Nodes should reach this call
  @Override
  public void matchReturnType(Type type) {
    returnType = type;
  }

  public Type getType(SymbolTable symbolTable) {
    return result.getType(symbolTable);
  }

  @Override
  public boolean endsWithReturn() {
    return true;
  }
}

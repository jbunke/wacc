package frontend.AbstractSyntaxTree.Statements;

import frontend.AbstractSyntaxTree.Expressions.ExpressionNode;
import frontend.SymbolTable.SemanticError;
import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.BaseTypes;
import frontend.SymbolTable.Types.Type;

public class WhileStatementNode extends StatementNode {

  private final ExpressionNode condition;
  private final StatementNode doStatement;

  public WhileStatementNode(ExpressionNode condition, StatementNode
          doStatement) {
    this.condition = condition;
    this.doStatement = doStatement;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    condition.semanticCheck(symbolTable, errorList);

    Type conditionType = condition.getType(symbolTable);
    if (conditionType == null) {
      errorList.addError(new SemanticError("Condition provided does not have a valid type"));
    } else if (!conditionType.equals(new BaseTypes(BaseTypes.base_types.BOOL))) {
      errorList.addError(new SemanticError("Condition provided is not a boolean."));
    }

    SymbolTable doStatementTable = symbolTable.newChild();
    doStatement.semanticCheck(doStatementTable, errorList);
  }

  @Override
  public boolean containsReturn() {
    return doStatement.containsReturn();
  }

  @Override
  public void matchReturnType(Type type) {
    doStatement.matchReturnType(type);
  }

  @Override
  public boolean endsWithReturn() {
    return doStatement.endsWithReturn();
  }

  @Override
  public boolean containsExit() {
    return doStatement.containsExit();
  }
}

package frontend.abstractSyntaxTree.statements;


import frontend.abstractSyntaxTree.expressions.ExpressionNode;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

public class IfStatementNode extends StatementNode {

  private final ExpressionNode condition;
  private final StatementNode trueBranch;
  private final StatementNode falseBranch;

  public IfStatementNode(ExpressionNode condition, StatementNode trueBranch,
                         StatementNode falseBranch) {
    this.condition = condition;
    this.trueBranch = trueBranch;
    this.falseBranch = falseBranch;
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

    SymbolTable trueBranchTable = symbolTable.newChild();
    trueBranch.semanticCheck(trueBranchTable, errorList);

    SymbolTable falseBranchTable = symbolTable.newChild();
    falseBranch.semanticCheck(falseBranchTable, errorList);
  }

  @Override
  public boolean containsReturn() {
    return trueBranch.containsReturn() || falseBranch.containsReturn();
  }

  @Override
  public void matchReturnType(Type type) {
    trueBranch.matchReturnType(type);
    falseBranch.matchReturnType(type);
  }

  @Override
  public boolean endsWithReturn() {
    return trueBranch.endsWithReturn() && falseBranch.endsWithReturn();
  }

  @Override
  public boolean containsExit() {
    return trueBranch.containsExit() || falseBranch.containsExit();
  }
}

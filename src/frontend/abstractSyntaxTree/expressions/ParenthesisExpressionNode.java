package frontend.abstractSyntaxTree.expressions;


import backend.Register;
import backend.instructions.Instruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;

import java.util.List;

public class ParenthesisExpressionNode extends ExpressionNode {
  private final ExpressionNode containedExpression;

  public ParenthesisExpressionNode(ExpressionNode i) {
    containedExpression = i;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    containedExpression.semanticCheck(symbolTable, errorList);
  }

  @Override
  public List<Instruction> generateAssembly(List<Register> registers, SymbolTable symbolTable) {
    return null;
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    return containedExpression.getType(symbolTable);
  }
}

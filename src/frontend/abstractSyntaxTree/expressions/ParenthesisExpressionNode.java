package frontend.abstractSyntaxTree.expressions;


import backend.AssemblyGenerator;
import backend.Register;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;

import java.util.Stack;
import shell.structural.Heap;

public class ParenthesisExpressionNode extends ExpressionNode {
  private final ExpressionNode containedExpression;

  public ParenthesisExpressionNode(ExpressionNode i) {
    containedExpression = i;
  }

  @Override
  public int weight() {
    return containedExpression.weight();
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    containedExpression.semanticCheck(symbolTable, errorList);
  }

  @Override
  public void generateAssembly(AssemblyGenerator generator,
                               SymbolTable symbolTable,
                               Stack<Register.ID> available) {
    containedExpression.generateAssembly(generator, symbolTable, available);
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    return containedExpression.getType(symbolTable);
  }

  @Override
  public Object evaluate(SymbolTable symbolTable, Heap heap) {
    return containedExpression.evaluate(symbolTable, heap);
  }

  @Override
  public String toString() {
    return "(" + containedExpression.toString() + ")";
  }
}

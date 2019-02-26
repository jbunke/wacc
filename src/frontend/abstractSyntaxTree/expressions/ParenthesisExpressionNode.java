package frontend.abstractSyntaxTree.expressions;


import backend.AssemblyGenerator;
import backend.Register;
import backend.instructions.Instruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;

import java.util.List;
import java.util.Stack;

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
  public List<Instruction> generateAssembly(AssemblyGenerator generator,
                                            SymbolTable symbolTable,
                                            Stack<Register.ID> available) {
    return containedExpression.generateAssembly(generator, symbolTable, available);
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    return containedExpression.getType(symbolTable);
  }
}

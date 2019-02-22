package frontend.abstractSyntaxTree.assignment;

import backend.AssemblyGeneratorVisitor;
import backend.Register;
import backend.instructions.Instruction;
import frontend.abstractSyntaxTree.expressions.ExpressionNode;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Pair;
import frontend.symbolTable.types.Type;

import java.util.List;
import java.util.Map;

public class NewPairNode implements AssignRHS {
  private final ExpressionNode leftExpression;
  private final ExpressionNode rightExpression;

  public NewPairNode(ExpressionNode leftExpression, ExpressionNode rightExpression) {
    this.leftExpression = leftExpression;
    this.rightExpression = rightExpression;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
  }

  @Override
  public List<Instruction> generateAssembly(AssemblyGeneratorVisitor assemblyGeneratorVisitor, SymbolTable symbolTable) {
    return null;
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    return new Pair(leftExpression.getType(symbolTable), rightExpression.getType(symbolTable));
  }
}

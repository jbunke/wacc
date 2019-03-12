package frontend.abstractSyntaxTree.expressions;


import backend.AssemblyGenerator;
import backend.Register;
import backend.instructions.LDRInstruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

import java.util.Stack;

public class IntLiteralExpressionNode extends ExpressionNode {
  private final int value;

  public IntLiteralExpressionNode(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  @Override
  public int weight() {
    return 1;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
  }

  @Override
  public void generateAssembly(AssemblyGenerator generator,
                               SymbolTable symbolTable,
                               Stack<Register.ID> available) {
    Register next = generator.getRegister(available.peek());
    generator.addInstruction(new LDRInstruction(next, value));
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    return new BaseTypes(BaseTypes.base_types.INT);
  }

  @Override
  public Object evaluate(SymbolTable symbolTable) {
    return value;
  }

  @Override
  public String toString() {
    return Integer.toString(value);
  }
}

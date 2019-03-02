package frontend.abstractSyntaxTree.expressions;


import backend.AssemblyGenerator;
import backend.Register;
import backend.instructions.MovInstruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

import java.util.Stack;

public class BooleanLiteralExpressionNode extends ExpressionNode {
  private final boolean value;

  public BooleanLiteralExpressionNode(boolean v) {
    value = v;
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
    generator.addInstruction(new MovInstruction(next, intRepresentation()));
  }

  private int intRepresentation() {
    if (value) {
      return 1;
    }
    return 0;
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    return new BaseTypes(BaseTypes.base_types.BOOL);
  }

  @Override
  public String toString() {
    return Boolean.toString(value);
  }
}

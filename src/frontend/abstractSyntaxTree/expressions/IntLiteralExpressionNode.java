package frontend.abstractSyntaxTree.expressions;


import backend.AssemblyGeneratorVisitor;
import backend.Register;
import backend.instructions.Instruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

import java.util.List;
import java.util.Map;
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
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
  }

  @Override
  public List<Instruction> generateAssembly(AssemblyGeneratorVisitor generator,
                                            SymbolTable symbolTable,
                                            Stack<Register.ID> available) {
    return null;
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    return new BaseTypes(BaseTypes.base_types.INT);
  }
}

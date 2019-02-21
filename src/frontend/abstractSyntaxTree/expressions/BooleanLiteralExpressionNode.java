package frontend.abstractSyntaxTree.expressions;


import backend.Register;
import backend.instructions.Instruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

import java.util.List;

public class BooleanLiteralExpressionNode extends ExpressionNode {
  private final boolean value;

  public BooleanLiteralExpressionNode(boolean v) {
    value = v;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
  }

  @Override
  public List<Instruction> generateAssembly(Map<Register.ID, Register> registers, SymbolTable symbolTable) {
    return null;
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    return new BaseTypes(BaseTypes.base_types.BOOL);
  }
}

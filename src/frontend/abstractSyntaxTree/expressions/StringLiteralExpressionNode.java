package frontend.abstractSyntaxTree.expressions;

import backend.Register;
import backend.instructions.Instruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Array;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

import java.util.List;

public class StringLiteralExpressionNode extends ExpressionNode {
  private final String value;

  public StringLiteralExpressionNode(String v) {
    value = v;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
  }

  @Override
  public List<Instruction> generateAssembly(List<Register> registers, SymbolTable symbolTable) {
    return null;
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    return new Array(new BaseTypes(BaseTypes.base_types.CHAR));
  }
}

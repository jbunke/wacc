package frontend.abstractSyntaxTree.expressions;

import backend.AssemblyGenerator;
import backend.Register;
import backend.instructions.LDRInstruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Array;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

import java.util.Stack;

public class StringLiteralExpressionNode extends ExpressionNode {
  private final String value;

  public StringLiteralExpressionNode(String v) {
    value = v.substring(1, v.length() - 1);
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
    Register first = new Register(available.peek());

    generator.addInstruction(new LDRInstruction(first, generator.addMsg(value)));
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    return new Array(new BaseTypes(BaseTypes.base_types.CHAR));
  }

  @Override
  public String toString() {
    return "\"" + value + "\"";
  }
}

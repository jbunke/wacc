package frontend.abstractSyntaxTree.expressions;


import backend.AssemblyGenerator;
import backend.Register;
import backend.instructions.Instruction;
import backend.instructions.LDRInstruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

import java.util.ArrayList;
import java.util.List;
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
  public List<Instruction> generateAssembly(AssemblyGenerator generator,
                                            SymbolTable symbolTable,
                                            Stack<Register.ID> available) {
    List<Instruction> instructions = new ArrayList<>();
    Register next = generator.getRegister(available.peek());
    instructions.add(new LDRInstruction(next, value));

    return instructions;
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    return new BaseTypes(BaseTypes.base_types.INT);
  }
}

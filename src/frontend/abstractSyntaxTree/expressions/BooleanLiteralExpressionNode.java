package frontend.abstractSyntaxTree.expressions;


import backend.AssemblyGenerator;
import backend.Register;
import backend.instructions.Instruction;
import backend.instructions.MovInstruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BooleanLiteralExpressionNode extends ExpressionNode {
  private final boolean value;

  public BooleanLiteralExpressionNode(boolean v) {
    value = v;
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
    instructions.add(new MovInstruction(next, intRepresentation()));

    return instructions;
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
}

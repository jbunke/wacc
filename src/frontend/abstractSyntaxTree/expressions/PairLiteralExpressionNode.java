package frontend.abstractSyntaxTree.expressions;


import backend.AssemblyGenerator;
import backend.Register;
import backend.instructions.LDRInstruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Pair;
import frontend.symbolTable.types.Type;

import java.util.Stack;

public class PairLiteralExpressionNode extends ExpressionNode {
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
    Register freeReg = generator.getRegister(available.peek());
    generator.addInstruction(new LDRInstruction(freeReg, 0));
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    return new Pair(null, null);
  }

  @Override
  public String toString() {
    return "null";
  }
}

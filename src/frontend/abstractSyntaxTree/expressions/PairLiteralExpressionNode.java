package frontend.abstractSyntaxTree.expressions;


import backend.AssemblyGenerator;
import backend.Register;
import backend.instructions.Instruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Pair;
import frontend.symbolTable.types.Type;

import java.util.ArrayList;
import java.util.List;
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
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    return new Pair(null, null);
  }
}

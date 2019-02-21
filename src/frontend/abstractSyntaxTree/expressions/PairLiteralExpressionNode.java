package frontend.abstractSyntaxTree.expressions;


import backend.Register;
import backend.instructions.Instruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Pair;
import frontend.symbolTable.types.Type;

import java.util.List;

public class PairLiteralExpressionNode extends ExpressionNode {
  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
  }

  @Override
  public List<Instruction> generateAssembly(List<Register> registers, SymbolTable symbolTable) {
    return null;
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    return new Pair(null, null);
  }
}

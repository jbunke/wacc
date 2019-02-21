package frontend.abstractSyntaxTree.typeNodes;

import backend.Register;
import backend.instructions.Instruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Pair;
import frontend.symbolTable.types.Type;

import java.util.List;
import java.util.Map;

public class PairTypeNode extends TypeNode {
  private final TypeNode firstType;
  private final TypeNode secondType;

  public PairTypeNode(TypeNode fstType, TypeNode sndType) {
    this.firstType = fstType;
    this.secondType = sndType;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    firstType.semanticCheck(symbolTable, errorList);
    secondType.semanticCheck(symbolTable, errorList);
  }

  @Override
  public List<Instruction> generateAssembly(Map<Register.ID, Register> registers, SymbolTable symbolTable) {
    return null;
  }

  @Override
  public Type getType() {
    return new Pair(firstType.getType(), secondType.getType());
  }
}

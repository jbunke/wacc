package frontend.abstractSyntaxTree.typeNodes;

import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Pair;
import frontend.symbolTable.types.Type;

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
  public Type getType() {
    return new Pair(firstType.getType(), secondType.getType());
  }
}

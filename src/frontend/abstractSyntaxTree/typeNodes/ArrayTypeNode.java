package frontend.abstractSyntaxTree.typeNodes;

import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Array;
import frontend.symbolTable.types.Type;

public class ArrayTypeNode extends TypeNode {
  private final TypeNode arrayType;

  public ArrayTypeNode(TypeNode arrayType) {
    this.arrayType = arrayType;
  }


  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    arrayType.semanticCheck(symbolTable, errorList);
  }

  @Override
  public Type getType() {
    // System.out.println("Array type: " + arrayType.getType());
    return new Array(arrayType.getType());
  }
}

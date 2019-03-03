package frontend.abstractSyntaxTree.typeNodes;

import backend.AssemblyGenerator;
import backend.Register;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Array;
import frontend.symbolTable.types.Type;

import java.util.Stack;

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
  public void generateAssembly(AssemblyGenerator generator,
                                            SymbolTable symbolTable,
                                            Stack<Register.ID> available) {
  }

  @Override
  public Type getType() {
    // System.out.println("Array type: " + arrayType.getType());
    return new Array(arrayType.getType());
  }

  @Override
  public String toString() {
    return arrayType.toString().toLowerCase();
  }
}

package frontend.abstractSyntaxTree.typeNodes;

import backend.Register;
import backend.instructions.Instruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Array;
import frontend.symbolTable.types.Type;

import java.util.List;

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
  public List<Instruction> generateAssembly(List<Register> registers, SymbolTable symbolTable) {
    return null;
  }

  @Override
  public Type getType() {
    // System.out.println("Array type: " + arrayType.getType());
    return new Array(arrayType.getType());
  }
}

package frontend.abstractSyntaxTree.typeNodes;

import backend.AssemblyGeneratorVisitor;
import backend.Register;
import backend.instructions.Instruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Array;
import frontend.symbolTable.types.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
  public List<Instruction> generateAssembly(AssemblyGeneratorVisitor generator,
                                            SymbolTable symbolTable,
                                            Stack<Register.ID> available) {
    return new ArrayList<>();
  }

  @Override
  public Type getType() {
    // System.out.println("Array type: " + arrayType.getType());
    return new Array(arrayType.getType());
  }
}

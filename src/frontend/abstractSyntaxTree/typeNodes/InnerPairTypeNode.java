package frontend.abstractSyntaxTree.typeNodes;


import backend.AssemblyGenerator;
import backend.Register;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.InnerPair;
import frontend.symbolTable.types.Type;

import java.util.Stack;

public class InnerPairTypeNode extends TypeNode {

  @Override
  public Type getType() {
    return new InnerPair();
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
  public String toString() {
    return "pair";
  }
}

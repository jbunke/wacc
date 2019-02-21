package frontend.abstractSyntaxTree.typeNodes;


import backend.Register;
import backend.instructions.Instruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.InnerPair;
import frontend.symbolTable.types.Type;

import java.util.List;

public class InnerPairTypeNode extends TypeNode {

  @Override
  public Type getType() {
    return new InnerPair();
  }


  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
  }

  @Override
  public List<Instruction> generateAssembly(List<Register> registers, SymbolTable symbolTable) {
    return null;
  }
}

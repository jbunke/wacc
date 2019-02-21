package frontend.abstractSyntaxTree.typeNodes;


import backend.Register;
import backend.instructions.Instruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.InnerPair;
import frontend.symbolTable.types.Type;

import java.util.List;
import java.util.Map;

public class InnerPairTypeNode extends TypeNode {

  @Override
  public Type getType() {
    return new InnerPair();
  }


  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
  }

  @Override
  public List<Instruction> generateAssembly(Map<Register.ID, Register> registers, SymbolTable symbolTable) {
    return null;
  }
}

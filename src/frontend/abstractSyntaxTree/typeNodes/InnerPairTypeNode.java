package frontend.abstractSyntaxTree.typeNodes;


import backend.AssemblyGenerator;
import backend.Register;
import backend.instructions.Instruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.InnerPair;
import frontend.symbolTable.types.Type;

import java.util.ArrayList;
import java.util.List;
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
  public List<Instruction> generateAssembly(AssemblyGenerator generator,
                                            SymbolTable symbolTable,
                                            Stack<Register.ID> available) {
    return new ArrayList<>();
  }
}

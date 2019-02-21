package frontend.abstractSyntaxTree;

import backend.Register;
import backend.instructions.Instruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;

import java.util.List;

public interface Node {
  void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList);
  List<Instruction> generateAssembly(List<Register> registers,
                                     SymbolTable symbolTable);
}
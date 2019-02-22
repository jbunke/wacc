package frontend.abstractSyntaxTree;

import backend.AssemblyGeneratorVisitor;
import backend.Register;
import backend.instructions.Instruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;

import java.util.List;
import java.util.Map;

public interface Node {
  void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList);

  List<Instruction> generateAssembly(AssemblyGeneratorVisitor assemblyGeneratorVisitor,
                                     SymbolTable symbolTable);
}
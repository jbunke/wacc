package frontend.abstractSyntaxTree;

import backend.AssemblyGenerator;
import backend.Register;
import backend.instructions.Instruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;

import java.util.List;
import java.util.Stack;

public interface Node {
  void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList);

  void generateAssembly(AssemblyGenerator generator,
                                     SymbolTable symbolTable,
                                     Stack<Register.ID> available);
}
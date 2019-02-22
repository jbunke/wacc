package frontend.abstractSyntaxTree.assignment;

import backend.AssemblyGeneratorVisitor;
import backend.instructions.Instruction;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;

import java.util.List;

public interface AssignRHS extends AssignLHS {
  Type getType(SymbolTable symbolTable);

  List<Instruction> generateAssembly(AssemblyGeneratorVisitor assemblyGeneratorVisitor,
                                     SymbolTable symbolTable);
}

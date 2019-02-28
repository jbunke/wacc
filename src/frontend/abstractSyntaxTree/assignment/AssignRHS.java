package frontend.abstractSyntaxTree.assignment;

import backend.AssemblyGenerator;
import backend.Register;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;

import java.util.Stack;

public interface AssignRHS extends AssignLHS {
  Type getType(SymbolTable symbolTable);

  void generateAssembly(AssemblyGenerator generator,
                                     SymbolTable symbolTable,
                                     Stack<Register.ID> available);
}

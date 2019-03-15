package frontend.abstractSyntaxTree.assignment;

import backend.AssemblyGenerator;
import backend.Register;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;
import shell.structural.Heap;

import java.util.Stack;

public interface AssignRHS extends AssignLHS {
  public static final int ADDR_SIZE = 4;

  Type getType(SymbolTable symbolTable);

  void generateAssembly(AssemblyGenerator generator,
                        SymbolTable symbolTable,
                        Stack<Register.ID> available);

  Object evaluate(SymbolTable symbolTable, Heap heap);
}

package frontend.abstractSyntaxTree.assignment;

import backend.AssemblyGeneratorVisitor;
import backend.Register;
import backend.instructions.Instruction;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;

import java.util.List;
import java.util.Stack;

public interface AssignRHS extends AssignLHS {
  Type getType(SymbolTable symbolTable);

  List<Instruction> generateAssembly(AssemblyGeneratorVisitor generator,
                                     SymbolTable symbolTable,
                                     Stack<Register.ID> available);
}

package frontend.abstractSyntaxTree.assignment;

import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;

public interface AssignRHS extends AssignLHS {
  Type getType(SymbolTable symbolTable);
}

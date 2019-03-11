package frontend.abstractSyntaxTree.assignment;

import frontend.abstractSyntaxTree.Node;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;

public interface AssignLHS extends Node {
  public static final int BYTE_SIZE = 1;

  Type getType(SymbolTable symbolTable);
}

package frontend.abstractSyntaxTree.assignment;

import frontend.abstractSyntaxTree.Node;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;

public interface AssignLHS extends Node {
    Type getType(SymbolTable symbolTable);
}

package frontend.AbstractSyntaxTree.Assignment;

import frontend.AbstractSyntaxTree.Node;
import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.Type;

public interface AssignRHS extends Node {
    Type getType(SymbolTable symbolTable);
}

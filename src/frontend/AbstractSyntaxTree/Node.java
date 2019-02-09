package frontend.AbstractSyntaxTree;

import frontend.SymbolTable.SymbolTable;

public interface Node {
    void semanticCheck(SymbolTable symbolTable);
}

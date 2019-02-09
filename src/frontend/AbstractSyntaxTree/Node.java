package frontend.AbstractSyntaxTree;

import frontend.SymbolTable.SymbolTable;

public interface Node {
    //TODO pass SemanticErrorList after merging SymbolTable
    void semanticCheck(SymbolTable symbolTable);
}

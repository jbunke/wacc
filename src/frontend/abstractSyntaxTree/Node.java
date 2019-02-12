package frontend.abstractSyntaxTree;

import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;

public interface Node {
    void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList);
}
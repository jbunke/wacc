package frontend.AbstractSyntaxTree.Expressions;

import frontend.AbstractSyntaxTree.Node;
import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.Type;

public abstract class ExpressionNode implements Node {

    public abstract void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList);

    public abstract Type getType(SymbolTable symbolTable);
}

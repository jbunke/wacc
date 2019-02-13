package frontend.abstractSyntaxTree.expressions;

import frontend.abstractSyntaxTree.Node;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;

public abstract class ExpressionNode implements Node {

  public abstract void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList);

  public abstract Type getType(SymbolTable symbolTable);
}

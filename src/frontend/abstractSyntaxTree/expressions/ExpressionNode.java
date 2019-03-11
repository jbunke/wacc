package frontend.abstractSyntaxTree.expressions;

import frontend.abstractSyntaxTree.assignment.AssignRHS;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;

public abstract class ExpressionNode implements AssignRHS {

  public abstract int weight();

  public abstract void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList);

  public abstract Type getType(SymbolTable symbolTable);
}

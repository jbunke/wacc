package frontend.abstractSyntaxTree.assignment;

import frontend.abstractSyntaxTree.expressions.IdentifierNode;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Pair;
import frontend.symbolTable.types.Type;

public class AssignPairElementNode implements AssignRHS {
  private final IdentifierNode identifier;
  private final int position;

  public AssignPairElementNode(IdentifierNode id, int position) {
    this.identifier = id;
    this.position = position;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    Type type = identifier.getType(symbolTable);
    if (!(type instanceof Pair)) {
      errorList.addError(new SemanticError("Not a pair type"));
    }
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    Pair pair = (Pair) identifier.getType(symbolTable);
    if (position == 0) {
      return pair.getFirst();
    } else {
      return pair.getSecond();
    }

  }

}

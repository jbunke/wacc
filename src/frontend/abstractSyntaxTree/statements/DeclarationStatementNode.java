package frontend.abstractSyntaxTree.statements;

import frontend.abstractSyntaxTree.assignment.AssignRHS;
import frontend.abstractSyntaxTree.expressions.IdentifierNode;
import frontend.abstractSyntaxTree.typeNodes.TypeNode;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;
import frontend.symbolTable.Variable;

public class DeclarationStatementNode extends StatementNode {

  private final TypeNode identifierType;
  private final IdentifierNode identifier;
  private final AssignRHS rhs;

  public DeclarationStatementNode(TypeNode idType, IdentifierNode identifier, AssignRHS rhs) {
    this.identifierType = idType;
    this.identifier = identifier;
    this.rhs = rhs;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    identifierType.semanticCheck(symbolTable, errorList);
    rhs.semanticCheck(symbolTable, errorList);

    final Type lhsType = identifierType.getType();
    final Type rhsType = rhs.getType(symbolTable);
    if (lhsType == null) {
      errorList.addError(new SemanticError("LHS type cannot be resolved"));
    } else if (rhsType == null) {
      errorList.addError(new SemanticError("RHS type cannot be resolved"));
    } else if (!lhsType.equals(rhsType)) {
      errorList.addError(new SemanticError("Declared type \""
          + lhsType.toString()
          + "\" does not match \""
          + rhsType.toString()
          + "\" on RHS."));
    }

    Variable variable = new Variable(identifierType.getType());
    symbolTable.add(identifier.getName(), variable);
  }

  IdentifierNode getIdentifier() {
    return identifier;
  }
}

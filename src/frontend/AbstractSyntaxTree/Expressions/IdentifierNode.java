package frontend.AbstractSyntaxTree.Expressions;

import frontend.SymbolTable.*;
import frontend.SymbolTable.Types.Type;

public class IdentifierNode extends ExpressionNode {
    private final String identifier;

    public IdentifierNode(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {

        Identifier id = symbolTable.fetchType(identifier);
        if (id == null) {
            errorList.addError(new SemanticError(
                    "Identifier \"" + identifier + "\" is used before it is declared."
            ));
        }

        else if (!(id instanceof Variable)) {
            errorList.addError(new SemanticError(
                    "Identifier \"" + this.identifier + "\" used as a variable incorrectly"
            ));
        }
    }

    public String getId() {
        return identifier;
    }

    @Override
    public Type getType(SymbolTable symbolTable) {
        Identifier ident = symbolTable.fetchType(identifier);
        if (ident == null || !(ident instanceof Variable)){
            return null;
        }
        return ((Variable) ident).getType();
    }
}

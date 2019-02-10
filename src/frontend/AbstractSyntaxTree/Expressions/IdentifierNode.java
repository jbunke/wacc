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

        IdentifierType id = symbolTable.getType(identifier);
        if (id == null) {
            errorList.addError(new SemanticError(
                    "Identifier \"" + identifier + "\" is used before it is declared."
            ));
        }

        else if (!(id == IdentifierType.VARIABLE)) {
            errorList.addError(new SemanticError(
                    "Identifier \"" + this.identifier + "\" used as a variable incorrectly"
            ));
        }
    }

    public String getId() {
        return identifier;
    }


    //TODO Design Decision: Identifier ENUM and Variable getters

    @Override
    public Type getType(SymbolTable symbolTable) {
        IdentifierType ident = symbolTable.getType(identifier);
        if (ident == null || !(ident ==  IdentifierType.VARIABLE)){
            return null;
        }
        return ((Variable) ident).getType(); // TODO ENUM and Class Clash
    }
}

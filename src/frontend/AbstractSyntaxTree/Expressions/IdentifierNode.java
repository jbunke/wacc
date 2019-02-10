package frontend.AbstractSyntaxTree.Expressions;

import com.sun.org.apache.xpath.internal.operations.Variable;
import frontend.SymbolTable.IdentifierType;
import frontend.SymbolTable.SemanticError;
import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;
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

    // TODO what should getType return here? Is it necessary?

    @Override
    public Type getType(SymbolTable symbolTable) {
        return null;
    }
}

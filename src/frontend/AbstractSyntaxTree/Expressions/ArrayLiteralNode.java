package frontend.AbstractSyntaxTree.Expressions;


import frontend.SymbolTable.SemanticError;
import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.Array;
import frontend.SymbolTable.Types.BaseTypes;
import frontend.SymbolTable.Types.Type;
import frontend.SymbolTable.Variable;

import java.util.List;

public class ArrayLiteralNode extends ExpressionNode {
    private final IdentifierNode identifier;
    private final List<ExpressionNode> indices;

    public ArrayLiteralNode(List<ExpressionNode> indices, IdentifierNode identifier) {
        this.indices = indices;
        this.identifier = identifier;
    }

    @Override
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
        for (ExpressionNode node : indices) {
            final Type nodeType = node.getType(symbolTable);
            if (!nodeType.equals(new BaseTypes(BaseTypes.base_types.INT))) {
                errorList.addError(new SemanticError(
                        "Error: Index used for array is not an integer!" + "" +
                                "Expression supplied was of type \"" + nodeType.toString() + "\"."
                ));
            }
        }

        final Type identifierType = identifier.getType(symbolTable);
        if (!(identifierType instanceof Array)) {
            errorList.addError(new SemanticError(
                    "Error: Tried to index a variable of non-array type. " +
                            "Variable is of type \"" + identifierType.toString() + "\"."
            ));
        }
    }

    @Override
    public Type getType(SymbolTable symbolTable) {
        Variable array = (Variable) symbolTable.fetchType(identifier.getId());
        Array current = (Array) array.getType();

        for (int i = indices.size(); i > 1; i--) {
            current = (Array) current.getElementType();
        }

        return current.getElementType();
    }
}

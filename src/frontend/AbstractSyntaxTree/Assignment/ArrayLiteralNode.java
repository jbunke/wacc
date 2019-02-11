package frontend.AbstractSyntaxTree.Assignment;


import frontend.AbstractSyntaxTree.Expressions.ExpressionNode;
import frontend.SymbolTable.SemanticError;
import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.Array;
import frontend.SymbolTable.Types.Type;

import java.util.List;

public class ArrayLiteralNode implements AssignRHS {
    private final List<ExpressionNode> arrayElements;

    public ArrayLiteralNode(List<ExpressionNode> arrayElements) {
        this.arrayElements = arrayElements;
    }

    @Override
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
        if (arrayElements.size() > 1) {
            final Type type = arrayElements.get(0).getType(symbolTable);

            for (ExpressionNode expr : arrayElements) {
                if (!expr.getType(symbolTable).equals(type)) {
                    errorList.addError(new SemanticError(
                            "Array with elements of multiple types not supported by WACC"
                    ));
                }
            }
        }
        for (ExpressionNode expr : arrayElements) {
            expr.semanticCheck(symbolTable, errorList);
        }
    }

    @Override
    public Type getType(SymbolTable symbolTable) {

        if (arrayElements.size() == 0) {
            return new Array(null);
        } else {
            return new Array(arrayElements.get(0).getType(symbolTable));
        }
    }
}
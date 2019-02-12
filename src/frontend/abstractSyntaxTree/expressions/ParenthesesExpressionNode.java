package frontend.abstractSyntaxTree.expressions;


import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;

public class ParenthesesExpressionNode extends ExpressionNode {
    private final ExpressionNode containedExpression;

    public ParenthesesExpressionNode(ExpressionNode i){
        containedExpression = i;
    }

    @Override
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
        containedExpression.semanticCheck(symbolTable, errorList);
    }

    @Override
    public Type getType(SymbolTable symbolTable) {
        return containedExpression.getType(symbolTable);
    }
}

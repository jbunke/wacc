package frontend.AbstractSyntaxTree.Assignment;
import frontend.AbstractSyntaxTree.Expressions.ExpressionNode;
import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.Pair;
import frontend.SymbolTable.Types.Type;

public class NewPairNode implements AssignLeft {
    private final ExpressionNode leftExpression;
    private final ExpressionNode rightExpression;

    public NewPairNode(ExpressionNode leftExpression, ExpressionNode rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    }

    @Override
    public Type getType(SymbolTable symbolTable) {
        return new Pair(leftExpression.getType(symbolTable), rightExpression.getType(symbolTable));
    }
}
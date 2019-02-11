package frontend.AbstractSyntaxTree.Statements;


import frontend.AbstractSyntaxTree.Expressions.ExpressionNode;
import frontend.SymbolTable.SemanticError;
import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;

public class PrintStatementNode extends StatementNode {
    private final ExpressionNode expression;

    public PrintStatementNode(ExpressionNode expr) {
        this.expression = expr;
    }

    @Override
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
        expression.semanticCheck(symbolTable, errorList);

        if (expression.getType(symbolTable) == null) {
            errorList.addError(new SemanticError("Type of expression given in \"print\" statement could not be resolved."));
        }
    }
}

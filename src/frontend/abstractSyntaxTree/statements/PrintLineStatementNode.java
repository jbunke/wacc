package frontend.abstractSyntaxTree.statements;


import frontend.abstractSyntaxTree.expressions.ExpressionNode;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;

public class PrintLineStatementNode extends StatementNode {
    private final ExpressionNode expression;

    public PrintLineStatementNode(ExpressionNode expr) {
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
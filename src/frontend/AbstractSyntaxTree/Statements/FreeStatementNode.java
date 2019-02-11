package frontend.AbstractSyntaxTree.Statements;

import frontend.AbstractSyntaxTree.Expressions.ExpressionNode;
import frontend.SymbolTable.SemanticError;
import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.Array;
import frontend.SymbolTable.Types.Pair;
import frontend.SymbolTable.Types.Type;

public class FreeStatementNode extends StatementNode {
    private final ExpressionNode expression;

    public FreeStatementNode(ExpressionNode expression) {
        this.expression = expression;
    }

    @Override
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
        expression.semanticCheck(symbolTable, errorList);

        Type exprType = expression.getType(symbolTable);
        if (exprType == null || (!(exprType instanceof Array) && !(exprType instanceof Pair))) {
            errorList.addError(new SemanticError("'free' call expected type: Array or Pair, but given type: " + exprType.toString()));
        }
    }
}

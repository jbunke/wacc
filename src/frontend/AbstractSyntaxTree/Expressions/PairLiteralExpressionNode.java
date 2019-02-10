package frontend.AbstractSyntaxTree.Expressions;


import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.Pair;
import frontend.SymbolTable.Types.Type;

public class PairLiteralExpressionNode extends ExpressionNode {
    @Override
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    }

    @Override
    public Type getType(SymbolTable symbolTable) {
        return new Pair(null, null);
    }
}

package frontend.AbstractSyntaxTree.Expressions;

import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.Array;
import frontend.SymbolTable.Types.BaseTypes;
import frontend.SymbolTable.Types.Type;

public class StringLiteralExpressionNode extends ExpressionNode {
    private final String value;

    public StringLiteralExpressionNode(String v){
        value = v;
    }

    @Override
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    }

    @Override
    public Type getType(SymbolTable symbolTable) {
        return new Array(new BaseTypes(BaseTypes.base_types.CHAR));
    }
}
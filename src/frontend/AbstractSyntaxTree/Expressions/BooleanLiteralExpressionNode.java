package frontend.AbstractSyntaxTree.Expressions;


import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.BaseTypes;
import frontend.SymbolTable.Types.Type;

public class BooleanLiteralExpressionNode extends ExpressionNode {
    private final boolean value;

    public BooleanLiteralExpressionNode(boolean v) {
        value = v;
    }

    @Override
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    }

    @Override
    public Type getType(SymbolTable symbolTable) {
        return new BaseTypes(BaseTypes.base_types.BOOL);
    }
}

package frontend.AbstractSyntaxTree.Expressions;


import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.BaseTypes;
import frontend.SymbolTable.Types.Type;

public class IntLiteralExpressionNode extends ExpressionNode {
    private final int value;

    public IntLiteralExpressionNode(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    @Override
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
        //intentionally left blank as this is a literal expression
    }

    @Override
    public Type getType(SymbolTable symbolTable) {
        return new BaseTypes(BaseTypes.base_types.INT);
    }
}

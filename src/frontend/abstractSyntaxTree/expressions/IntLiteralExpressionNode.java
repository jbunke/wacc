package frontend.abstractSyntaxTree.expressions;


import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

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
    }

    @Override
    public Type getType(SymbolTable symbolTable) {
        return new BaseTypes(BaseTypes.base_types.INT);
    }
}

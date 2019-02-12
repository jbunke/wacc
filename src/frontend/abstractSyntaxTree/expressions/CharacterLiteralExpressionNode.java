package frontend.abstractSyntaxTree.expressions;

import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

public class CharacterLiteralExpressionNode extends ExpressionNode {
    private final char value;

    public CharacterLiteralExpressionNode(char v){
        value = v;
    }

    @Override
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    }

    @Override
    public Type getType(SymbolTable symbolTable) {
        return new BaseTypes(BaseTypes.base_types.CHAR);
    }
}

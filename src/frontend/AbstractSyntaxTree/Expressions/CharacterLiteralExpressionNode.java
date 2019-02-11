package frontend.AbstractSyntaxTree.Expressions;

import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.BaseTypes;
import frontend.SymbolTable.Types.Type;

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

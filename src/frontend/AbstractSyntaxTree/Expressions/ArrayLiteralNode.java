package frontend.AbstractSyntaxTree.Expressions;

import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.Type;
//TODO Implement ArrayLiteral Node
public class ArrayLiteralNode extends ExpressionNode{
    @Override
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {

    }

    @Override
    public Type getType(SymbolTable symbolTable) {
        return null;
    }
}

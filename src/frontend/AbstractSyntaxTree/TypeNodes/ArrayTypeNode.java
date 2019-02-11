package frontend.AbstractSyntaxTree.TypeNodes;

import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.Array;
import frontend.SymbolTable.Types.Type;

public class ArrayTypeNode extends TypeNode {
    private final TypeNode arrayType;

    public ArrayTypeNode(TypeNode arrayType) {
        this.arrayType = arrayType;
    }


    @Override
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
        arrayType.semanticCheck(symbolTable, errorList);
    }

    @Override
    public Type getType() {
        return new Array(arrayType.getType());
    }
}

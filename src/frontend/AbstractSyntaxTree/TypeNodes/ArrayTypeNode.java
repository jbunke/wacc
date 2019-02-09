package frontend.AbstractSyntaxTree.TypeNodes;

import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.Array;
import frontend.SymbolTable.Types.Type;

public class ArrayTypeNode extends TypeNode {
    private final TypeNode arrayType;

    public ArrayTypeNode(TypeNode arrayType) {
        this.arrayType = arrayType;
    }

    //TODO pass SemanticErrorList after merging SymbolTable

    @Override
    public void semanticCheck(SymbolTable symbolTable) {
        arrayType.semanticCheck(symbolTable);
    }

    @Override
    public Type getType() {
        return new Array(arrayType.getType());
    }
}

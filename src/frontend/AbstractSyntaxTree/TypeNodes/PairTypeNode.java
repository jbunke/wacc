package frontend.AbstractSyntaxTree.TypeNodes;

import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.Pair;
import frontend.SymbolTable.Types.Type;

public class PairTypeNode extends TypeNode {
    private final TypeNode firstType;
    private final TypeNode secondType;

    public PairTypeNode(TypeNode fstType, TypeNode sndType) {
        this.firstType = fstType;
        this.secondType = sndType;
    }

    @Override
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
        firstType.semanticCheck(symbolTable, errorList);
        secondType.semanticCheck(symbolTable, errorList);
    }

    @Override
    public Type getType() {
        return new Pair(firstType.getType(), secondType.getType());
    }
}

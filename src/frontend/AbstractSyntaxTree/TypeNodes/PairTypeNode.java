package frontend.AbstractSyntaxTree.TypeNodes;

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

    //TODO pass SemanticErrorList after merging SymbolTable
    @Override
    public void semanticCheck(SymbolTable symbolTable) {
        firstType.semanticCheck(symbolTable);
        secondType.semanticCheck(symbolTable);
    }

    @Override
    public Type getType() {
        return new Pair(firstType.getType(), secondType.getType());
    }
}

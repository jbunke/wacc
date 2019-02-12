package frontend.abstractSyntaxTree.typeNodes;


import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.InnerPair;
import frontend.symbolTable.types.Type;

public class InnerPairTypeNode extends TypeNode {

    @Override
    public Type getType() {
        return new InnerPair();
    }


    @Override
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    }
}

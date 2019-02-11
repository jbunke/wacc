package frontend.AbstractSyntaxTree.TypeNodes;


import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.InnerPair;
import frontend.SymbolTable.Types.Type;

public class InnerPairTypeNode extends TypeNode {

    @Override
    public Type getType() {
        return new InnerPair();
    }


    @Override
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    }
}

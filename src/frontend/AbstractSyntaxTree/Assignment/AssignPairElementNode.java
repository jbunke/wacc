package frontend.AbstractSyntaxTree.Assignment;

import frontend.AbstractSyntaxTree.Expressions.IdentifierNode;
import frontend.SymbolTable.SemanticError;
import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.Pair;
import frontend.SymbolTable.Types.Type;

public class AssignPairElementNode implements Assign {
    private final IdentifierNode identifier;
    private final int position;

    public AssignPairElementNode(IdentifierNode id, int position) {
        this.identifier = id;
        this.position = position;
    }


    @Override
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
        Type type = identifier.getType(symbolTable);
        if (!(type instanceof Pair)) {
            errorList.addError(new SemanticError("Not a pair type"));
        }
    }

    @Override
    public Type getType(SymbolTable symbolTable) {
        Pair pair = (Pair) identifier.getType(symbolTable);
        if (position == 0) {
            return pair.getFirst();
        } else {
            return pair.getSecond();
        }

    }

}

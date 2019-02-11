package frontend.AbstractSyntaxTree.Statements;


import frontend.AbstractSyntaxTree.Node;
import frontend.SymbolTable.Types.Type;

public abstract class StatementNode implements Node {
    public boolean endsWithReturn() {
        return false;
    }

    public boolean containsReturn() {
        return false;
    }

    public boolean containsExit() {
        return false;
    }

    public void matchReturnType(Type type) {
    }
}

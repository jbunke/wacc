package frontend.abstractSyntaxTree.statements;


import frontend.abstractSyntaxTree.Node;
import frontend.symbolTable.types.Type;

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

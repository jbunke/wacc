package frontend.abstractSyntaxTree.typeNodes;

import frontend.abstractSyntaxTree.Node;
import frontend.symbolTable.types.Type;

public abstract class TypeNode implements Node {
    public abstract Type getType();
}

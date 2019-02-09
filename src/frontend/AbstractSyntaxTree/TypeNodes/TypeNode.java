package frontend.AbstractSyntaxTree.TypeNodes;

import frontend.AbstractSyntaxTree.Node;
import frontend.SymbolTable.Types.Type;

public abstract class TypeNode implements Node {
    public abstract Type getType();
}

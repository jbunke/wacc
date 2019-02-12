package frontend.symbolTable;

import frontend.symbolTable.types.Type;

public class Function extends Identifier {

    // TODO arguments and getArgs function (after AST Nodes)
    private final Type returnType;

    public Function(Type returnType) {
        this.returnType = returnType;
    }

    public Type getReturnType() {
        return returnType;
    }
}

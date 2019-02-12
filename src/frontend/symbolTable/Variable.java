package frontend.symbolTable;


import frontend.symbolTable.types.Type;

public class Variable extends Identifier {
    private final Type type;

    public Variable(Type type) {
        this.type = type;
    }

    public Type getType(){
        return type;
    }
}
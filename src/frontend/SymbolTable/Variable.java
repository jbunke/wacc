package frontend.SymbolTable;


import frontend.SymbolTable.Types.Type;

public class Variable extends Identifier{
    private final Type type;

    public Variable(Type type) {
        this.type = type;
    }

    public Type getType(){
        return type;
    }
}
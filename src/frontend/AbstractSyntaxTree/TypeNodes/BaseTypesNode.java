package frontend.AbstractSyntaxTree.TypeNodes;

import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.Array;
import frontend.SymbolTable.Types.BaseTypes;
import frontend.SymbolTable.Types.Type;

public class BaseTypesNode extends TypeNode {
    private final Type type;

    @Override
    public Type getType() {
        return type;
    }

    public BaseTypesNode(String type) {
        this.type = stringToType(type);
    }

    private Type stringToType(String type) {
        switch (type) {
            case "int":
                return new BaseTypes(BaseTypes.base_types.INT);
            case "char":
                return new BaseTypes(BaseTypes.base_types.CHAR);
            case "bool":
                return new BaseTypes(BaseTypes.base_types.BOOL);
            case "string":
                return new Array(new BaseTypes(BaseTypes.base_types.CHAR));
            default:
                throw new IllegalArgumentException("Illegal type name has been given: " + type);
        }
    }

    //TODO pass SemanticErrorList after merging SymbolTable
    @Override
    public void semanticCheck(SymbolTable symbolTable) {
    }
}

package frontend.abstractSyntaxTree.typeNodes;

import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Array;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

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

    @Override
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    }
}
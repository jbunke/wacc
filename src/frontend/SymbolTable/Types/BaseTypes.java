package frontend.SymbolTable.Types;

public class BaseTypes extends Type {
    public enum base_types {
        INT, BOOL, CHAR
    }

    private final base_types baseType;

    public BaseTypes(base_types base_type) {
        this.baseType = base_type;
    }

    @Override
    public boolean equals(Type obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof BaseTypes) {
            BaseTypes base = (BaseTypes) obj;
            return base.baseType.equals(this.baseType);
        }

        return false;
    }

    @Override
    public String toString() {
        return baseType.toString();
    }
}

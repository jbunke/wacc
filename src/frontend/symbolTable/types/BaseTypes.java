package frontend.symbolTable.types;

public class BaseTypes extends Type {

  private static final int BOOL_SIZE = 1;
  private static final int CHAR_SIZE = 1;
  public static final int INT_SIZE = 4;

  public enum base_types {
    INT, BOOL, CHAR
  }

  private final base_types baseType;

  public base_types getBaseType() {
    return baseType;
  }

  public BaseTypes(base_types base_type) {
    this.baseType = base_type;
  }

  @Override
  public boolean equals(Type obj) {
    if (obj instanceof BaseTypes) {
      BaseTypes base = (BaseTypes) obj;
      return base.baseType.equals(this.baseType);
    }

    return false;
  }

  @Override
  public int size() {
    switch (baseType) {
      case CHAR:
        return CHAR_SIZE;
      case BOOL:
        return BOOL_SIZE;
      case INT:
        return INT_SIZE;
      default:
        return 0;
    }
  }

  @Override
  public String toString() {
    return baseType.toString().toLowerCase();
  }
}

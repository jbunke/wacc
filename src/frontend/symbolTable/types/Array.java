package frontend.symbolTable.types;

public class Array extends Type {
  private static final int ARRAY_SIZE = 4;
  private final Type elementType;

  public Array(Type elemsType) {
    this.elementType = elemsType;
  }

  public Type getElementType() {
    return elementType;
  }

  @Override
  public boolean equals(Type other) {
    if (other == this) {
      return true;
    }

    if (other instanceof Array) {
      Array array = (Array) other;
      return elementType == null || array.elementType == null || elementType.equals(array.elementType);

    } else {
      return false;
    }
  }

  @Override
  public int size() {
    return ARRAY_SIZE;
  }

  @Override
  public String toString() {
    return "Array[" + elementType.toString() + "]";
  }
}

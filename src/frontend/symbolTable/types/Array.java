package frontend.symbolTable.types;

public class Array extends Type {
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
  public String toString() {
    return "Array[" + elementType.toString() + "]";
  }
}

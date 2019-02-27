package frontend.symbolTable.types;

public abstract class Type {
  public abstract boolean equals(Type obj);

  public abstract int size();

  public boolean isSingleByte(){
    return size() == 1;
  }
}
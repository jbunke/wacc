package frontend.symbolTable.types;

public class InnerPair extends Type {
  @Override
  public boolean equals(Type obj) {
    return obj instanceof Pair || obj instanceof InnerPair;
  }

  @Override
  public int size() {
    return 0; // TODO
  }

  @Override
  public String toString() {
    return "pair";
  }
}

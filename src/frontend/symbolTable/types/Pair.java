package frontend.symbolTable.types;

public class Pair extends Type {
  private static final int PAIR_SIZE = 4;

  private final Type first;
  private final Type second;

  public Pair(Type fst, Type snd) {
    this.first = fst;
    this.second = snd;
  }

  public Type getFirst() {
    return first;
  }

  public Type getSecond() {
    return second;
  }

  @Override
  public boolean equals(Type other) {
    if (other == this) {
      return true;
    }

    if (other instanceof InnerPair) {
      return true;
    }

    if (other instanceof Pair) {
      Pair pair = (Pair) other;

      return checkElementEquality(first, pair.first) &&
              checkElementEquality(second, pair.second);
    }

    //obj not an instance of pair
    return false;
  }

  @Override
  public int size() {
    return PAIR_SIZE;
  }

  private boolean checkElementEquality(Type x, Type y) {
    return x == null || y == null || x.equals(y);
  }

  @Override
  public String toString() {
    String a = first == null ? "pair" : first.toString();
    String b = second == null ? "pair" : second.toString();

    return "pair(" + a + ", " + b + ")";
  }
}

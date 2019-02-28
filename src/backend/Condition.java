package backend;

public enum Condition {
  L,      // link
  EQ, NE, // equality
  GT, GE, // greater than, greater than or equal to
  LT, LE, // less than, less than or equal to
  AL;      // always

  public Condition opposite() {
    switch (this) {
      case EQ:
        return NE;
      case NE:
        return EQ;
      case GT:
        return LE;
      case GE:
        return LT;
      case LE:
        return GT;
      case LT:
        return GE;
      default:
        return this;
    }
  }
}

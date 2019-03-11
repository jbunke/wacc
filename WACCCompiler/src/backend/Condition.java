package backend;

public enum Condition {
  L,      // link
  EQ, NE, // equality
  GT, GE, // greater than, greater than or equal to
  LT, LE, // less than, less than or equal to
  VS, VC, // overflow set, overflow clear
  CS, CC, // unsigned higher or same, unsigned lower
  MI, PL, // negative, positive or zero
  HI, LS, // unsigned higher, unsigned lower or same
  S;

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
      case VS:
        return VC;
      case VC:
        return VS;
      case CS:
        return CC;
      case CC:
        return CS;
      case MI:
        return PL;
      case PL:
        return MI;
      case HI:
        return LS;
      case LS:
        return HI;
      default:
        return this;
    }
  }
}

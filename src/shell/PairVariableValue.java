package shell;


public class PairVariableValue {

  private String addr;

  private Object left;
  private Object right;

  public PairVariableValue(String addr, Object left, Object right) {
    this.addr = addr;
    this.left = left;
    this.right = right;
  }

  public Object getLeft() {
    return left;
  }

  public Object getRight() {
    return right;
  }

  public void setLeft(Object newLeft) {
    left = newLeft;
  }

  public void setRight(Object newRight) {
    right = newRight;
  }

  @Override
  public String toString() {
    return addr;
  }
}

package shell;

import frontend.abstractSyntaxTree.expressions.ExpressionNode;
import java.util.List;

public class ArrayVariableValue {
  public static final int MIN_ARRAY_INDEX = 0;

  private final String startAddr;
  private List<Object> elements;

  public ArrayVariableValue(String startAddr, List<Object> elements) {
    this.startAddr = startAddr;
    this.elements = elements;
  }

  public Object getElementAtIndex(int index) {
    return elements.get(index);
  }

  public boolean indexInUpperBound(int index) {
    return elements.size() > index;
  }

  public boolean indexInLowerBound(int index) {
    return index >= MIN_ARRAY_INDEX;
  }

  public void updateElement(int index, Object value) {
    elements.set(index, value);
  }

  public int getLength() {
    return elements.size();
  }

  @Override
  public String toString() {
    return startAddr;
  }
}

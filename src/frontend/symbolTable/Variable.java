package frontend.symbolTable;

import frontend.symbolTable.types.Type;

public class Variable extends SymbolCategory {
  private final Type type;
  private Object value;

  public Variable(Type type) {
    this.type = type;
  }

  public Type getType() {
    return type;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }
}
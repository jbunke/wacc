package frontend.symbolTable;

import frontend.symbolTable.types.Type;

public class Variable extends SymbolCategory {
  private final Type type;
  private Object value;
  private VarType varType;

  public enum VarType {
    INT, BOOL, STRING, CHAR
  }

  public static int size(Variable variable) {
    switch (variable.varType) {
      case INT:
      case STRING:
        return 4;
      case BOOL:
      case CHAR:
        return 1;
      default:
        return 0;
    }
  }

  public Variable(Type type) {
    this.type = type;
  }

  public Type getType() {
    return type;
  }

  public Object getValue() {
    return value;
  }

  public VarType getVarType() {
    return varType;
  }

  public void setValue(Object value) {
    this.value = value;
  }
}
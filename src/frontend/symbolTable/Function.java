package frontend.symbolTable;

import frontend.abstractSyntaxTree.typeNodes.ParameterListNode;
import frontend.symbolTable.types.Type;

import java.util.List;

public class Function extends SymbolCategory {

  private final Type returnType;
  private final List<Type> parameters;

  public List<Type> getParameters() {
    return parameters;
  }

  public Function(Type returnType, ParameterListNode parameters) {
    this.returnType = returnType;
    this.parameters = parameters.getParamTypes();
  }

  public Type getReturnType() {
    return returnType;
  }
}

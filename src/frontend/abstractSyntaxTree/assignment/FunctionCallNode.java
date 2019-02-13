package frontend.abstractSyntaxTree.assignment;

import frontend.abstractSyntaxTree.expressions.ExpressionNode;
import frontend.abstractSyntaxTree.expressions.IdentifierNode;
import frontend.symbolTable.Function;
import frontend.symbolTable.SymbolCategory;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;

import java.util.List;

public class FunctionCallNode implements AssignRHS {

  private final IdentifierNode functionIdentifier;
  private final List<ExpressionNode> arguments;
  private Function function;

  public FunctionCallNode(IdentifierNode functionIdentifier, List<ExpressionNode> arguments) {
    this.functionIdentifier = functionIdentifier;
    this.arguments = arguments;
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    Function function = (Function) symbolTable.find(functionIdentifier.getName());
    this.function = function;
    return function.getReturnType();
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    SymbolCategory function = symbolTable.find(functionIdentifier.getName());
    if (function == null) {
      errorList.addError(new SemanticError("This function does not exist"));
      return;
    }

    if (!(function instanceof Function)) {
      errorList.addError(new SemanticError("This is not a function"));
      return;
    }

    Function f = (Function) function;

    if (f.getParameters().size() >= arguments.size()) {
      errorList.addError(new SemanticError("Too few arguments"));
    } else if (f.getParameters().size() <= arguments.size()) {
      errorList.addError(new SemanticError(("Too many arguments")));
    } else {
      List<Type> parameters = f.getParameters();
      for (int i = 0; i < arguments.size(); i++) {
        ExpressionNode node = arguments.get(i);

        Type nodeType = node.getType(symbolTable);
        Type funcArgType = parameters.get(i);

        if (!(funcArgType.equals(nodeType))) {
          errorList.addError(new SemanticError(
                  "Argument type was incorrect. Provided argument type was \"" +
                          (nodeType == null ? "NULL" : nodeType.toString()) +
                          "\" but expected type is \"" +
                          funcArgType.toString() + "\"."
          ));
        }
      }
      this.function = (Function) function;
    }

  }
}

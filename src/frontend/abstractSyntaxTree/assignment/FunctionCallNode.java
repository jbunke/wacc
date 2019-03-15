package frontend.abstractSyntaxTree.assignment;

import backend.AssemblyGenerator;
import backend.Condition;
import backend.Register;
import backend.instructions.ArithInstruction;
import backend.instructions.BranchInstruction;
import backend.instructions.MovInstruction;
import backend.instructions.STRInstruction;
import frontend.abstractSyntaxTree.expressions.ExpressionNode;
import frontend.abstractSyntaxTree.expressions.IdentifierNode;
import frontend.abstractSyntaxTree.typeNodes.FunctionDefinitionNode;
import frontend.symbolTable.*;
import frontend.symbolTable.types.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import shell.structural.Heap;

public class FunctionCallNode implements AssignRHS {
  private final IdentifierNode functionIdentifier;
  private final List<ExpressionNode> arguments;
  private FunctionDefinitionNode function;

  public FunctionCallNode(IdentifierNode functionIdentifier, List<ExpressionNode> arguments) {
    this.functionIdentifier = functionIdentifier;
    this.arguments = arguments;
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    FunctionDefinitionNode function =
            (FunctionDefinitionNode) symbolTable.find(functionIdentifier.getName());
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

    if (!(function instanceof FunctionDefinitionNode)) {
      errorList.addError(new SemanticError("This is not a function"));
      return;
    }

    FunctionDefinitionNode f = (FunctionDefinitionNode) function;

    if (f.getParameterList().getParamTypes().size() > arguments.size()) {
      errorList.addError(new SemanticError("Too few arguments"));
    } else if (f.getParameterList().getParamTypes().size() < arguments.size()) {
      errorList.addError(new SemanticError(("Too many arguments")));
    } else {
      List<Type> parameters = f.getParameterList().getParamTypes();
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
      this.function = f;
    }
  }

  @Override
  public void generateAssembly(AssemblyGenerator generator,
                               SymbolTable symbolTable, Stack<Register.ID> available) {
    // Arguments stored in negative stack addresses
    int totalSize = 0;
    for (int i = arguments.size() - 1; i >= 0; i--) {
      ExpressionNode argument = arguments.get(i);
      argument.generateAssembly(generator, symbolTable, available);
      int size = argument.getType(symbolTable).size();
      generator.addInstruction(new STRInstruction(
              generator.getRegister(available.peek()),
              generator.getRegister(Register.ID.SP), -size, size == 1
      ).addExclamation());
      symbolTable.incrementArgLoadingOffset(size);
      totalSize += size;
    }
    symbolTable.resetArgLoadingOffset();

    // Branch to function
    String funcLabel = "f_" + functionIdentifier.getName();
    generator.addInstruction(new BranchInstruction(Condition.L, funcLabel));

    // Stack de-allocation from function arguments
    while (totalSize > 0) {
      generator.addInstruction(ArithInstruction.add(
              generator.getRegister(Register.ID.SP),
              generator.getRegister(Register.ID.SP), Math.min(1024, totalSize)));
      totalSize = Math.max(0, totalSize - 1024);
    }

    // Move result (R0) into first available general purpose since it's a call
    generator.addInstruction(new MovInstruction(
            generator.getRegister(available.peek()),
            generator.getRegister(Register.ID.R0)));
  }

  @Override
  public Object evaluate(SymbolTable symbolTable, Heap heap) {
    // TODO

    SymbolTable rootTable = symbolTable.getParent();

    List<Object> argValues = new ArrayList<>();

    for (ExpressionNode expression : arguments) {
      argValues.add(expression.evaluate(symbolTable, heap));
    }

    SymbolTable functionTable = rootTable.getChild(function);

    List<String> parameters = function.getParameterList().getIdentifiers();

    for (int i = 0; i < argValues.size(); i++) {
      functionTable.setValue(parameters.get(i), argValues.get(i));
    }

    return function.getBody().applyStatement(functionTable, heap).value;
  }
}

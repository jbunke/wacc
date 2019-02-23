package frontend.abstractSyntaxTree.assignment;


import backend.AssemblyGeneratorVisitor;
import backend.Register;
import backend.instructions.Instruction;
import frontend.abstractSyntaxTree.expressions.ExpressionNode;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Array;
import frontend.symbolTable.types.Type;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ArrayLiteralNode implements AssignRHS {
  private final List<ExpressionNode> arrayElements;

  public ArrayLiteralNode(List<ExpressionNode> arrayElements) {
    this.arrayElements = arrayElements;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    if (arrayElements.size() > 1) {
      final Type type = arrayElements.get(0).getType(symbolTable);

      for (ExpressionNode expr : arrayElements) {
        if (!expr.getType(symbolTable).equals(type)) {
          errorList.addError(new SemanticError(
                  "Array with elements of multiple types not supported by WACC"
          ));
        }
      }
    }
    for (ExpressionNode expr : arrayElements) {
      expr.semanticCheck(symbolTable, errorList);
    }
  }

  @Override
  public List<Instruction> generateAssembly(AssemblyGeneratorVisitor generator,
                                            SymbolTable symbolTable,
                                            Stack<Register.ID> available) {
    return null;
  }

  @Override
  public Type getType(SymbolTable symbolTable) {

    if (arrayElements.size() == 0) {
      return new Array(null);
    } else {
      return new Array(arrayElements.get(0).getType(symbolTable));
    }
  }
}

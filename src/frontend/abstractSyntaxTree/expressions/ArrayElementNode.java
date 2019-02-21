package frontend.abstractSyntaxTree.expressions;


import backend.Register;
import backend.instructions.Instruction;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Array;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;
import frontend.symbolTable.Variable;

import java.util.List;

public class ArrayElementNode extends ExpressionNode {
  private final IdentifierNode identifier;
  private final List<ExpressionNode> indices;

  public ArrayElementNode(List<ExpressionNode> indices, IdentifierNode identifier) {
    this.indices = indices;
    this.identifier = identifier;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    for (ExpressionNode node : indices) {
      final Type nodeType = node.getType(symbolTable);
      if (!nodeType.equals(new BaseTypes(BaseTypes.base_types.INT))) {
        errorList.addError(new SemanticError(
                "Error: Index used for array is not an integer!" + "" +
                        "Expression supplied was of type \"" + nodeType.toString() + "\"."
        ));
      }
    }

    final Type identifierType = identifier.getType(symbolTable);
    if (!(identifierType instanceof Array)) {
      errorList.addError(new SemanticError(
              "Error: Tried to index a variable of non-array type. " +
                      "Variable is of type \"" + identifierType.toString() + "\"."
      ));
    }

    int dimensions = 0;
    Type t = identifierType;
    while (t instanceof Array) {
      t = ((Array) t).getElementType();
      dimensions++;
    }

    if (indices.size() > dimensions) {
      errorList.addError(new SemanticError(
              "Error: Too many indices! Array only has" + dimensions + " dimensions, " +
                      "but has been supplied" + indices.size() + " indices."
      ));
    }
  }

  @Override
  public List<Instruction> generateAssembly(Map<Register.ID, Register> registers, SymbolTable symbolTable) {
    return null;
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    Variable array = (Variable) symbolTable.find(identifier.getName());
    Array current = (Array) array.getType();

    for (int i = indices.size(); i > 1; i--) {
      current = (Array) current.getElementType();
    }

    return current.getElementType();
  }
}

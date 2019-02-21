package frontend.abstractSyntaxTree.expressions;

import backend.Register;
import backend.instructions.Instruction;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BinaryOpExpressionNode extends ExpressionNode {
  private final static Map<String, OperatorType> stringOpMap = Map.ofEntries(
          Map.entry("*", OperatorType.TIMES),
          Map.entry("/", OperatorType.DIVIDE),
          Map.entry("%", OperatorType.MOD),
          Map.entry("+", OperatorType.PLUS),
          Map.entry("-", OperatorType.MINUS),
          Map.entry(">", OperatorType.GREATER_THAN),
          Map.entry(">=", OperatorType.GREATER_THAN_OR_EQUAL),
          Map.entry("<", OperatorType.LESS_THAN),
          Map.entry("<=", OperatorType.LESS_THAN_OR_EQUAL),
          Map.entry("==", OperatorType.EQUAL),
          Map.entry("!=", OperatorType.NOT_EQUAL),
          Map.entry("&&", OperatorType.AND),
          Map.entry("||", OperatorType.OR)
  );

  private final ExpressionNode left;
  private final ExpressionNode right;
  private final OperatorType operatorType;

  public BinaryOpExpressionNode(ExpressionNode left, String operatorType, ExpressionNode right) {
    this.left = left;
    this.operatorType = stringToType(operatorType);
    this.right = right;
  }

  private enum OperatorType {
    TIMES(2),
    DIVIDE(2),
    MOD(2),
    PLUS(2),
    MINUS(2),
    GREATER_THAN(1),
    GREATER_THAN_OR_EQUAL(1),
    LESS_THAN(1),
    LESS_THAN_OR_EQUAL(1),
    EQUAL(0),
    NOT_EQUAL(0),
    AND(0),
    OR(0);

        /* 0 - Boolean operators that take boolean arguments
         * 1 - Boolean operators that take arithmetic arguments
         * 2 - Arithmetic operators */

    public final int value;

    OperatorType(int value) {
      this.value = value;
    }
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    final Type leftType = left.getType(symbolTable);
    final Type rightType = right.getType(symbolTable);


    if (leftType == null) {
      errorList.addError(new SemanticError(
              "Invalid type on the left of " + operatorType.toString()
      ));
    }

    if (rightType == null) {
      errorList.addError(new SemanticError(
              "Invalid type on the right of " + operatorType.toString()
      ));
    }

    if (leftType != null && rightType != null) {
      if (!leftType.equals(rightType)) {
        errorList.addError(new SemanticError(
                "Binary operator \"" + operatorType.toString() + "\" given operands of different types. "
                        + "types given are \"" + leftType.toString() + "\" and \""
                        + rightType.toString() + "\"."
        ));
      }

      List<Type> expected = new ArrayList<>();
      if (operatorType == OperatorType.AND || operatorType == OperatorType.OR) {
        expected.add(new BaseTypes(BaseTypes.base_types.BOOL));
      }

      if (operatorType.value > 0) {
        expected.add(new BaseTypes(BaseTypes.base_types.INT));
      }

      if (operatorType.value == 1) {
        expected.add(new BaseTypes(BaseTypes.base_types.CHAR));
      }

      boolean found = false;
      for (Type t : expected) {
        if (t.equals(leftType)) {
          found = true;
          break;
        }
      }

      if (!(found || operatorType == OperatorType.EQUAL || operatorType == OperatorType.NOT_EQUAL)) {
        errorList.addError(new SemanticError(
                "Possible expected operands: " + expected.toString() +
                        " but received operands of type: \"" + leftType.toString() +
                        "\" for binary operator: " + operatorType.toString() + "."
        ));
      }
    }

    left.semanticCheck(symbolTable, errorList);
    right.semanticCheck(symbolTable, errorList);
  }

  @Override
  public List<Instruction> generateAssembly(Map<Register.ID, Register> registers, SymbolTable symbolTable) {
    return null;
  }

  public Type getType(SymbolTable symbolTable) {
//    Type leftType = left.getType(symbolTable);
//    Type rightType = right.getType(symbolTable);
//
//    if (leftType == null || rightType == null || !leftType.equals(rightType)) {
//      return null;
//    }

    switch (operatorType) {
      case GREATER_THAN:
      case GREATER_THAN_OR_EQUAL:
      case LESS_THAN:
      case LESS_THAN_OR_EQUAL:
      case EQUAL:
      case NOT_EQUAL:
      case AND:
      case OR:
        return new BaseTypes(BaseTypes.base_types.BOOL);
      case TIMES:
      case DIVIDE:
      case MOD:
      case PLUS:
      case MINUS:
      default:
        return new BaseTypes(BaseTypes.base_types.INT);
//      default:
//        return null;
    }
  }

  private static OperatorType stringToType(String operator) {
    if (stringOpMap.containsKey(operator)) {
      return stringOpMap.get(operator);
    }
    throw new IllegalArgumentException();
  }
}

package frontend.abstractSyntaxTree.expressions;

import backend.AssemblyGenerator;
import backend.Condition;
import backend.Register;
import backend.instructions.*;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import shell.Heap;

public class BinaryOpExpressionNode extends ExpressionNode {
  private final static String OVERFLOW = "OverflowError: the result is too " +
          "small/large to store in a 4-byte signed-integer.\\n";

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

  private final static Map<OperatorType, Condition> equivalent =
          Map.ofEntries(
                  Map.entry(OperatorType.EQUAL, Condition.EQ),
                  Map.entry(OperatorType.NOT_EQUAL, Condition.NE),
                  Map.entry(OperatorType.GREATER_THAN_OR_EQUAL, Condition.GE),
                  Map.entry(OperatorType.GREATER_THAN, Condition.GT),
                  Map.entry(OperatorType.LESS_THAN_OR_EQUAL, Condition.LE),
                  Map.entry(OperatorType.LESS_THAN, Condition.LT)
          );

  private final ExpressionNode left;
  private final ExpressionNode right;
  private final OperatorType operatorType;

  private static final String DIV_BY_ZERO_ERR = "DivideByZeroError: " +
          "divide or modulo by zero\\n\\0";


  public BinaryOpExpressionNode(ExpressionNode left, String operatorType, ExpressionNode right) {
    this.left = left;
    this.operatorType = stringToType(operatorType);
    this.right = right;
  }

  public enum OperatorType {
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

    public String toString() {
      switch (this) {
        case EQUAL:
          return " == ";
        case NOT_EQUAL:
          return " != ";
        case GREATER_THAN:
          return " > ";
        case GREATER_THAN_OR_EQUAL:
          return " >= ";
        case LESS_THAN:
          return " < ";
        case LESS_THAN_OR_EQUAL:
          return " <= ";
        case AND:
          return " && ";
        case OR:
          return " || ";
        case PLUS:
          return " + ";
        case MINUS:
          return " - ";
        case TIMES:
          return " * ";
        case DIVIDE:
          return " / ";
        case MOD:
          return " % ";
        default:
          return " ";
      }
    }
  }

  @Override
  public int weight() {
    return 1 + left.weight() + right.weight();
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
  public void generateAssembly(AssemblyGenerator generator,
                               SymbolTable symbolTable,
                               Stack<Register.ID> available) {
    Stack<Register.ID> originalRegState = new Stack<>();
    originalRegState.addAll(available);

    Register first = generator.getRegister(available.pop());
    Register second = generator.getRegister(available.pop());

    if (left.weight() > right.weight()) {
      generateOperands(left, right, second, first,
              generator, symbolTable, available);
    } else {
      generateOperands(right, left, first, second,
              generator, symbolTable, available);
    }
    available.pop();

    generateOperation(first, second, generator);
    available.clear();
    available.addAll(originalRegState);
  }

  private void generateOperation(Register rg1, Register rg2,
                                 AssemblyGenerator generator) {
    Register r0 = generator.getRegister(Register.ID.R0);
    Register r1 = generator.getRegister(Register.ID.R1);
    switch (operatorType) {
      case AND:
        generator.addInstruction(new AndInstruction(rg1, rg1, rg2));
        break;
      case OR:
        generator.addInstruction(new OrInstruction(rg1, rg1, rg2));
        break;
      case EQUAL:
      case NOT_EQUAL:
      case GREATER_THAN:
      case GREATER_THAN_OR_EQUAL:
      case LESS_THAN:
      case LESS_THAN_OR_EQUAL:
        Condition cond = equivalent.get(operatorType);
        Condition complement = cond.opposite();
        generator.addInstruction(new CompareInstruction(rg1, rg2));
        generator.addInstruction(new MovInstruction(rg1, 1)
                .withCondition(cond));
        generator.addInstruction(new MovInstruction(rg1, 0)
                .withCondition(complement));
        break;
      case TIMES:
        generator.addInstruction(new SMULLInstruction(rg1, rg2, rg1, rg2));
        generator.addInstruction(new CompareInstruction(rg2, rg1, 31));
        generator.generateLabel("p_throw_overflow_error",
                new String[]{OVERFLOW},
                AssemblyGenerator::throw_overflow_error);
        generator.addInstruction(
                new BranchInstruction(List.of(Condition.L, Condition.NE),
                        "p_throw_overflow_error"));
        break;
      case DIVIDE:
        generateDivByZeroCheck(generator);
        generator.addInstruction(new MovInstruction(r0, rg1));
        generator.addInstruction(new MovInstruction(r1, rg2));
        generator.addInstruction(new BranchInstruction(Condition.L,
                "p_check_divide_by_zero"));
        generator.addInstruction(new BranchInstruction(Condition.L,
                "__aeabi_idiv"));
        generator.addInstruction(new MovInstruction(rg1, r0));
        break;
      case MOD:
        generator.addInstruction(new MovInstruction(r0, rg1));
        generator.addInstruction(new MovInstruction(r1, rg2));
        generateDivByZeroCheck(generator);
        generator.addInstruction(new BranchInstruction(Condition.L,
                "p_check_divide_by_zero"));
        generator.addInstruction(new BranchInstruction(Condition.L,
                "__aeabi_idivmod"));
        generator.addInstruction(new MovInstruction(rg1, r1));
        break;
      case PLUS:
        generator.addInstruction(ArithInstruction.addReg(rg1, rg1, rg2).withS());
        List<Condition> blvs = List.of(Condition.L, Condition.VS);
        generator.generateLabel("p_throw_overflow_error",
                new String[]{OVERFLOW}, AssemblyGenerator::throw_overflow_error);
        generator.addInstruction(new BranchInstruction(blvs,
                "p_throw_overflow_error"));
        break;
      case MINUS:
        generator.addInstruction(ArithInstruction.subReg(rg1, rg1, rg2).withS());
        blvs = List.of(Condition.L, Condition.VS);
        generator.generateLabel("p_throw_overflow_error",
                new String[]{OVERFLOW}, AssemblyGenerator::throw_overflow_error);
        generator.addInstruction(new BranchInstruction(blvs,
                "p_throw_overflow_error"));
        break;
    }
  }

  private static void generateDivByZeroCheck(AssemblyGenerator generator) {
    generator.generateLabel("p_check_divide_by_zero",
            new String[]{DIV_BY_ZERO_ERR},
            AssemblyGenerator::check_divide_by_zero);
  }

  private void generateOperands(ExpressionNode op1,
                                ExpressionNode op2, Register rg1, Register rg2,
                                AssemblyGenerator generator, SymbolTable symbolTable,
                                Stack<Register.ID> available) {
    available.push(rg1.getRegID());
    available.push(rg2.getRegID());
    op1.generateAssembly(generator, symbolTable, available);
    available.pop();
    op2.generateAssembly(generator, symbolTable, available);
  }

  public Type getType(SymbolTable symbolTable) {
//    Type leftType = left.getType(symbolTable);
//    Type rightType = right.getType(symbolTable);
//
//    if (leftType == null || rightType == null || !leftType.equals(rightType)) {
//      return new ArrayList<>();
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
//        return new ArrayList<>();
    }
  }

  @Override
  public Object evaluate(SymbolTable symbolTable, Heap heap) {
    Object left = this.left.evaluate(symbolTable, heap);
    Object right = this.right.evaluate(symbolTable, heap);
    switch (operatorType) {
      case PLUS:
        return (Integer) left + (Integer) right;
      case MINUS:
        return (Integer) left - (Integer) right;
      case TIMES:
        return (Integer) left * (Integer) right;
      case DIVIDE:
        return (Integer) left / (Integer) right;
      case MOD:
        return (Integer) left % (Integer) right;
      case AND:
        return (Boolean) left && (Boolean) right;
      case OR:
        return (Boolean) left || (Boolean) right;
      case EQUAL:
        return left == right;
      case NOT_EQUAL:
        return left != right;
      case GREATER_THAN:
        return (Integer) left > (Integer) right;
      case GREATER_THAN_OR_EQUAL:
        return (Integer) left >= (Integer) right;
      case LESS_THAN:
        return (Integer) left < (Integer) right;
      case LESS_THAN_OR_EQUAL:
        return (Integer) left <= (Integer) right;
      default:
        return null;
    }
  }

  private static OperatorType stringToType(String operator) {
    if (stringOpMap.containsKey(operator)) {
      return stringOpMap.get(operator);
    }
    throw new IllegalArgumentException();
  }

  @Override
  public String toString() {
    return left.toString() + operatorType.toString() + right.toString();
  }
}

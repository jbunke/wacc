package frontend.abstractSyntaxTree.expressions;

import backend.AssemblyGenerator;
import backend.Condition;
import backend.Register;
import backend.instructions.*;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Array;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class UnaryOpExpressionNode extends ExpressionNode {
  private final static String OVERFLOW = "OverflowError: the result is too " +
          "small/large to store in a 4-byte signed-integer.\\n";

  private final static Map<String, OperatorType> stringOpMap = Map.ofEntries(
          Map.entry("!", OperatorType.NOT),
          Map.entry("+", OperatorType.POSITIVE),
          Map.entry("-", OperatorType.NEGATIVE),
          Map.entry("len", OperatorType.LENGTH),
          Map.entry("ord", OperatorType.ORD),
          Map.entry("chr", OperatorType.CHR)
  );

  private final OperatorType operatorType;
  private final ExpressionNode operand;

  public UnaryOpExpressionNode(String operatorType, ExpressionNode operand) {
    this.operatorType = stringToType(operatorType);
    this.operand = operand;
  }

  private enum OperatorType {
    NOT,
    NEGATIVE,
    POSITIVE,
    LENGTH,
    ORD,
    CHR
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    switch (operatorType) {
      case NOT:
        return new BaseTypes(BaseTypes.base_types.BOOL);

      case NEGATIVE:
      case POSITIVE:
      case ORD:
      case LENGTH:
        return new BaseTypes(BaseTypes.base_types.INT);

      case CHR:
        return new BaseTypes(BaseTypes.base_types.CHAR);
    }
    return null;
  }

  private Type getOperandType() {
    switch (operatorType) {
      case NOT:
        return new BaseTypes(BaseTypes.base_types.BOOL);

      case NEGATIVE:
      case POSITIVE:
      case CHR:
        return new BaseTypes(BaseTypes.base_types.INT);

      case ORD:
      default:
        return new BaseTypes(BaseTypes.base_types.CHAR);
    }
  }

  @Override
  public int weight() {
    return 1 + operand.weight();
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    Type operandType = operand.getType(symbolTable);
    if (operatorType == OperatorType.LENGTH) {
      if (!(operandType instanceof Array)) {
        errorList.addError(new SemanticError(
                "expected array type: " +
                        " but received type: \"" + operandType.toString() +
                        "\" for unary operator LENGTH."
        ));
      }
      return;
    }

    Type expected = getOperandType();

    if (!operandType.equals(expected)) {
      errorList.addError(new SemanticError(
              "expected type: \"" + expected.toString() +
                      "\" but given type: \"" + operandType.toString() +
                      "\" for unary operator " + operatorType.toString() + "."
      ));
    }
  }

  @Override
  public void generateAssembly(AssemblyGenerator generator,
                                            SymbolTable symbolTable,
                                            Stack<Register.ID> available) {
    switch (operatorType) {
      case NOT:
        addNotInstructions(generator, symbolTable, available);
        break;
      case CHR:
      case ORD:
        operand.generateAssembly(generator, symbolTable, available);
        break;
      case NEGATIVE:
        Register first = generator.getRegister(available.peek());
        operand.generateAssembly(generator, symbolTable, available);
        generator.addInstruction(new RSBSInstruction(first, first, 0));
        List<Condition> blvs = List.of(Condition.L, Condition.VS);
        generator.generateLabel("p_throw_overflow_error",
                new String[] {OVERFLOW}, AssemblyGenerator::throw_overflow_error);
        generator.addInstruction(new BranchInstruction(blvs,
                "p_throw_overflow_error"));
        break;
    }
  }

  private void addNotInstructions(AssemblyGenerator generator,
                                  SymbolTable symbolTable,
                                  Stack<Register.ID> available) {
    // Type is boolean
    operand.generateAssembly(generator, symbolTable, available);
    Register first = generator.getRegister(available.peek());
    generator.addInstruction(new ExOrInstruction(first, first, 1));
  }

  private OperatorType stringToType(String operator) {
    if (stringOpMap.containsKey(operator)) {
      return stringOpMap.get(operator);
    }
    throw new IllegalArgumentException();
  }
}

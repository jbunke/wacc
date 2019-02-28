package frontend.abstractSyntaxTree.expressions;

import backend.AssemblyGenerator;
import backend.Register;
import backend.instructions.ExOrInstruction;
import backend.instructions.Instruction;
import backend.instructions.LDRInstruction;
import backend.instructions.RSBSInstruction;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Array;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class UnaryOpExpressionNode extends ExpressionNode {
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
  public List<Instruction> generateAssembly(AssemblyGenerator generator,
                                            SymbolTable symbolTable,
                                            Stack<Register.ID> available) {
    List<Instruction> instructions = new ArrayList<>();

    switch (operatorType) {
      case NOT:
        addNotInstructions(generator, symbolTable, available, instructions);
        break;
      case CHR:
      case ORD:
        instructions.addAll(
                operand.generateAssembly(generator, symbolTable, available));
        break;
      case NEGATIVE:
        Register first = generator.getRegister(available.peek());
        Register sp = generator.getRegister(Register.ID.SP);
        instructions.add(new LDRInstruction(first, sp));
        instructions.add(new RSBSInstruction(first, first, 0));
        // TODO: Add BLVS
        break;
      case LENGTH:
        // TODO: Implement once arrays are implemented
        break;
    }

    return instructions;
  }

  private void addNotInstructions(AssemblyGenerator generator,
                                  SymbolTable symbolTable,
                                  Stack<Register.ID> available,
                                  List<Instruction> instructions) {
    // Type is boolean
    instructions.addAll(operand.generateAssembly(generator, symbolTable, available));
    Register first = generator.getRegister(available.peek());
    instructions.add(new ExOrInstruction(first, first, 1));
  }

  private OperatorType stringToType(String operator) {
    if (stringOpMap.containsKey(operator)) {
      return stringOpMap.get(operator);
    }
    throw new IllegalArgumentException();
  }
}

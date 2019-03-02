package backend.instructions;

import backend.Condition;
import backend.Register;

import java.util.ArrayList;
import java.util.List;

public class ArithInstruction extends Instruction {

  private String code;
  private final Register destRegister;
  private final Register sourceRegister;
  private final String operand;
  private final String leftShift;
  private List<Condition> conditions;

  public static ArithInstruction addReg(Register destRegister, Register opReg1,
      Register opReg2) {
    return addReg(destRegister, opReg1, opReg2, 0);
  }

  public static ArithInstruction addReg(Register destRegister, Register opReg1,
      Register opReg2, int leftShift) {
    String leftShiftStr = leftShift != 0 ? Integer.toString(leftShift) : "";

    ArithInstruction ins = new ArithInstruction(destRegister, opReg1, opReg2,
        leftShiftStr);
    ins.code = "ADD";
    return ins;
  }

  public static ArithInstruction subReg(Register destRegister,
      Register opReg1,
      Register opReg2) {
    ArithInstruction ins =
        new ArithInstruction(destRegister, opReg1, opReg2, "");
    ins.code = "SUB";
    return ins;
  }

  public static ArithInstruction add(Register destRegister,
      Register sourceRegister, int operand) {
    ArithInstruction ins =
        new ArithInstruction(destRegister, sourceRegister, operand);
    ins.code = "ADD";
    return ins;
  }

  public static ArithInstruction sub(Register destRegister,
      Register sourceRegister, int operand) {
    ArithInstruction ins =
        new ArithInstruction(destRegister, sourceRegister, operand);
    ins.code = "SUB";
    return ins;
  }

  private ArithInstruction(Register destRegister,
      Register sourceRegister, int operand) {
    this.destRegister = destRegister;
    this.sourceRegister = sourceRegister;
    this.operand = "#" + Integer.toString(operand);
    this.conditions = new ArrayList<>();
    this.leftShift = "";
  }

  private ArithInstruction(Register destRegister,
      Register opReg1, Register opReg2, String leftShift) {
    this.destRegister = destRegister;
    this.sourceRegister = opReg1;
    this.operand = opReg2.toString();
    this.conditions = new ArrayList<>();
    this.leftShift = leftShift;
  }

  public ArithInstruction withS() {
    conditions.add(Condition.S);
    return this;
  }

  @Override
  public String asString() {
    StringBuilder sb = new StringBuilder(code);
    conditions.forEach(sb::append);
    sb.append(" ");
    sb.append(destRegister.toString());
    sb.append(", ");
    sb.append(sourceRegister.toString());
    sb.append(", ");
    sb.append(operand);
    if (!leftShift.equals("")) {
      sb.append(", LSL #");
      sb.append(leftShift);
    }
    return sb.toString();
  }
}

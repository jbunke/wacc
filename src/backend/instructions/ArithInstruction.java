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
  private List<Condition> conditions;

  public static ArithInstruction addReg(Register destRegister,
                                        Register opReg1,
                                        Register opReg2) {
    ArithInstruction ins =
            new ArithInstruction(destRegister, opReg1, opReg2);
    ins.code = "ADD";
    return ins;
  }

  public static ArithInstruction subReg(Register destRegister,
                                        Register opReg1,
                                        Register opReg2) {
    ArithInstruction ins =
            new ArithInstruction(destRegister, opReg1, opReg2);
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
  }

  private ArithInstruction(Register destRegister,
                           Register opReg1, Register opReg2) {
    this.destRegister = destRegister;
    this.sourceRegister = opReg1;
    this.operand = opReg2.toString();
    this.conditions = new ArrayList<>();
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
    return sb.toString();
  }
}

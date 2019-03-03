package backend.instructions;

import backend.Register;

public class CompareInstruction extends Instruction {
  private final Register operand1;
  private final String operand2;
  private final String asrConst;

  public CompareInstruction(Register register, int operand) {
    this.operand1 = register;
    this.operand2 = "#" + Integer.toString(operand);
    asrConst = null;
  }

  public CompareInstruction(Register operand1, Register operand2) {
    this.operand1 = operand1;
    this.operand2 = operand2.toString();
    asrConst = null;
  }

  public CompareInstruction(Register operand1, Register operand2, int asrConst) {
    this.operand1 = operand1;
    this.operand2 = operand2.toString();
    this.asrConst = Integer.toString(asrConst);
  }

  @Override
  public String asString() {
    if (asrConst != null) {
      return "CMP " + operand1.toString() + ", " + operand2 + ", ASR #" + asrConst;
    }
    return "CMP " + operand1.toString() + ", " + operand2;
  }
}

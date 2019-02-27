package backend.instructions;

import backend.Condition;
import backend.Register;

public class CompareInstruction extends Instruction {
  private final Register operand1;
  private final String operand2;

  public CompareInstruction(Register register, int operand) {
    this.operand1 = register;
    this.operand2 = "#" + Integer.toString(operand);
  }

  public CompareInstruction(Register operand1, Register operand2) {
    this.operand1 = operand1;
    this.operand2 = operand2.toString();
  }

  @Override
  public String asString() {
    return "CMP " + operand1.toString() + ", " + operand2;
  }
}

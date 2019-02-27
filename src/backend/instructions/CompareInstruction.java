package backend.instructions;

import backend.Register;

public class CompareInstruction extends Instruction {
  private Register register;
  private String operand;

  public CompareInstruction(Register register, int operand) {
    this.register = register;
    this.operand = "#" + Integer.toString(operand);
  }

  @Override
  public String asString() {
    return "CMP " + register.toString() + ", " + operand;
  }
}

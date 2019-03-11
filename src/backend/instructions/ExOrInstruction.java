package backend.instructions;

import backend.Register;

public class ExOrInstruction extends Instruction {

  private final Register destRegister;
  private final Register sourceRegister;
  private final int operand;

  public ExOrInstruction(Register destRegister, Register sourceRegister, int operand) {
    this.destRegister = destRegister;
    this.sourceRegister = sourceRegister;
    this.operand = operand;
  }

  @Override
  public String asString() {
    return "EOR " + destRegister + ", " + sourceRegister + ", #" + operand;
  }

}

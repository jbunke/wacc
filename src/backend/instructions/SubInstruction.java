package backend.instructions;

import backend.Register;

public class SubInstruction extends Instruction {
  private final Register destRegister;
  private final Register sourceRegister;
  private final String operand;

  public SubInstruction(Register destRegister,
                        Register sourceRegister, int operand) {
    this.destRegister = destRegister;
    this.sourceRegister = sourceRegister;
    this.operand = "#" + Integer.toString(operand);
  }

  @Override
  public String asString() {
    return "SUB " + destRegister.toString() + ", " +
            sourceRegister.toString() + ", " + operand;
  }
}

package backend.instructions;

import backend.Register;

public class AddInstruction extends Instruction {
  private final Register destRegister;
  private final Register sourceRegister;
  private final String operand;

  public AddInstruction(Register destRegister,
                        Register sourceRegister, int operand) {
    this.destRegister = destRegister;
    this.sourceRegister = sourceRegister;
    this.operand = "#" + Integer.toString(operand);
  }

  @Override
  public String asString() {
    return "ADD " + destRegister.toString() + ", " +
            sourceRegister.toString() + ", " + operand;
  }
}

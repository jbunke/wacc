package backend.instructions;

import backend.Register;

public class AndInstruction extends Instruction {
  private final Register destRegister;
  private final Register op1;
  private final Register op2;

  public AndInstruction(Register destRegister,
                        Register op1, Register op2) {
    this.destRegister = destRegister;
    this.op1 = op1;
    this.op2 = op2;
  }

  @Override
  public String asString() {
    return "AND " + destRegister.toString() + ", " +
            op1.toString() + ", " + op2.toString();
  }
}

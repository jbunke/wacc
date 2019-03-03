package backend.instructions;

import backend.Register;

public class OrInstruction extends Instruction {
  private final Register destRegister;
  private final Register op1;
  private final Register op2;

  public OrInstruction(Register destRegister,
                       Register op1, Register op2) {
    this.destRegister = destRegister;
    this.op1 = op1;
    this.op2 = op2;
  }

  @Override
  public String asString() {
    return "ORR " + destRegister.toString() + ", " +
            op1.toString() + ", " + op2.toString();
  }
}

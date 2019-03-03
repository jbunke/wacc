package backend.instructions;

import backend.Register;

public class SMULLInstruction extends Instruction {

  private Register rdLoRegister;
  private Register rdHiRegister;
  private Register rNRegister;
  private Register rMRegister;

  public SMULLInstruction(Register rdLoRegister, Register rdHiRegister, Register rNRegister, Register rMRegister) {
    this.rdLoRegister = rdLoRegister;
    this.rdHiRegister = rdHiRegister;
    this.rNRegister = rNRegister;
    this.rMRegister = rMRegister;
  }

  @Override
  public String asString() {
    return "SMULL " + rdLoRegister + ", " + rdHiRegister + ", " + rNRegister + ", " + rMRegister;
  }
}

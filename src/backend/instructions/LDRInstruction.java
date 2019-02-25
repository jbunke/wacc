package backend.instructions;

import backend.Register;

public class LDRInstruction extends Instruction {

  private Register destRegister;
  private int constant;
  private Register indexRegister;
  private int offset;
  private boolean isConstant;

  public LDRInstruction(Register destRegister, int constant) {
    this.destRegister = destRegister;
    this.constant = constant;
    this.isConstant = true;
  }

  public LDRInstruction(Register destRegister, Register indexRegister,
                        int offset) {
    this.destRegister = destRegister;
    this.indexRegister = indexRegister;
    this.offset = offset;
    this.isConstant = false;
  }

  public LDRInstruction(Register destRegister, Register indexRegister) {
    this.destRegister = destRegister;
    this.indexRegister = indexRegister;
    this.offset = 0;
    this.isConstant = false;
  }

  @Override
  public String asString() {
    if (isConstant) {
      return "LDR " + destRegister.toString() + ", =" + constant;
    } else if (offset == 0) {
      return "LDR " + destRegister.toString() + ", [" +
              indexRegister.toString() + "]";
    }
    return "LDR " + destRegister.toString() + ", [" +
            indexRegister.toString() + ", #" + offset + "]";
  }
}

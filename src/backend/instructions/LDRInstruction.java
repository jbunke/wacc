package backend.instructions;

import backend.Condition;
import backend.Register;

public class LDRInstruction extends Instruction {

  private Register destRegister;
  private String constant;
  private Register indexRegister;
  private int offset;
  private boolean isConstant;
  private Condition condition = null;
  private boolean isSingleByte = false;

  public LDRInstruction(Register destRegister, int constant) {
    this.destRegister = destRegister;
    this.constant = Integer.toString(constant);
    this.isConstant = true;
  }

  public LDRInstruction(Condition condition,
                        Register destRegister, String constant) {
    this.condition = condition;
    this.destRegister = destRegister;
    this.constant = constant;
    this.isConstant = true;
  }

  public LDRInstruction(Register destRegister, String constant) {
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

  public LDRInstruction withCondition(Condition condition) {
    this.condition = condition;
    return this;
  }

  public LDRInstruction isSingleByte(boolean isSingleByte){
    this.isSingleByte = isSingleByte;
    return this;
  }

  @Override
  public String asString() {
    String ldrCmmd = isSingleByte ? "LDRSB " : "LDR ";

    if (condition == null) {
      if (isConstant) {
        return ldrCmmd + destRegister.toString() + ", =" + constant;
      } else if (offset == 0) {
        return ldrCmmd + destRegister.toString() + ", [" +
                indexRegister.toString() + "]";
      }
      return ldrCmmd + destRegister.toString() + ", [" +
              indexRegister.toString() + ", #" + offset + "]";
    } else {
      if (isConstant) {
        return ldrCmmd + condition.name() + " " +
                destRegister.toString() + ", =" + constant;
      } else if (offset == 0) {
        return ldrCmmd + condition.name() + " " +
                destRegister.toString() + ", [" +
                indexRegister.toString() + "]";
      }
      return ldrCmmd + condition.name() + " " + destRegister.toString() + ", [" +
              indexRegister.toString() + ", #" + offset + "]";
    }
  }
}

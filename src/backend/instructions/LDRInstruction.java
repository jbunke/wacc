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

  @Override
  public String asString() {
    if (condition == null) {
      if (isConstant) {
        return "LDR " + destRegister.toString() + ", =" + constant;
      } else if (offset == 0) {
        return "LDR " + destRegister.toString() + ", [" +
                indexRegister.toString() + "]";
      }
      return "LDR " + destRegister.toString() + ", [" +
              indexRegister.toString() + ", #" + offset + "]";
    } else {
      if (isConstant) {
        return "LDR" + condition.name() + " " +
                destRegister.toString() + ", =" + constant;
      } else if (offset == 0) {
        return "LDR" + condition.name() + " " +
                destRegister.toString() + ", [" +
                indexRegister.toString() + "]";
      }
      return "LDR" + condition.name() + " " + destRegister.toString() + ", [" +
              indexRegister.toString() + ", #" + offset + "]";
    }
  }
}

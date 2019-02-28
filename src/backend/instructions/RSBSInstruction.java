package backend.instructions;

import backend.Register;

public class RSBSInstruction extends Instruction {

  private Register destReg;
  private Register operand1;
  private int operand2;

  public RSBSInstruction(Register destReg, Register operand1, int operand2) {
    this.destReg = destReg;
    this.operand1 = operand1;
    this.operand2 = operand2;
  }

  @Override
  public String asString() {
    return "RSBS " + destReg + ", " + operand1 + ", #" + operand2;
  }
}

package backend.instructions;

import backend.Register;

public class MovInstruction extends Instruction {

  private Register dest;
  private String op;

  public MovInstruction(Register dest, int immediate) {
    this.dest = dest;
    this.op = "#" + Integer.toString(immediate);
  }

  public MovInstruction(Register dest, Register src) {
    this.dest = dest;
    this.op = src.toString();
  }

  @Override
  public String asString() {
    return "MOV " + dest.toString() + ", " + op;
  }
}

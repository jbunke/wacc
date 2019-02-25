package backend.instructions;

import backend.Register;

public class MovInstruction extends Instruction {

  private Register dest;
  private String op;

  public MovInstruction(Register dest, int immediate) {
    this.dest = dest;
    this.op = "#" + Integer.toString(immediate);
  }

  public MovInstruction(Register dest, char immediate) {
    this.dest = dest;
    this.op = "#'" + immediate + "'";
  }

  public MovInstruction(Register dest, boolean immediate) {
    this.dest = dest;
    this.op = "#" + boolToStringAssembly(immediate);
  }

  public MovInstruction(Register dest, String immediate) {
    this.dest = dest;
    this.op = "#\"" + immediate + "\"";
  }

  public MovInstruction(Register dest, Register src) {
    this.dest = dest;
    this.op = src.toString();
  }

  private String boolToStringAssembly(boolean value) {
    if (value) {
      return "1";
    }
    return "0";
  }

  @Override
  public String asString() {
    return "MOV " + dest.toString() + ", " + op;
  }
}

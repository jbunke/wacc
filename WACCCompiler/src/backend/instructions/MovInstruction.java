package backend.instructions;

import backend.Condition;
import backend.Register;

public class MovInstruction extends Instruction {

  private final Register dest;
  private final String op;

  private Condition condition;

  public MovInstruction(Register dest, int immediate) {
    this.dest = dest;
    this.op = "#" + Integer.toString(immediate);
  }

  public MovInstruction(Register dest, char immediate) {
    this.dest = dest;
    if (immediate == '\0') {
      this.op = "#0";
    } else {
      this.op = "#'" + immediate + "'";
    }
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
    return value ? "1" : "0";
  }

  public MovInstruction withCondition(Condition condition) {
    this.condition = condition;
    return this;
  }

  @Override
  public String asString() {
    String cmmnd = (condition == null) ? "MOV " : "MOV" + condition.name() + " ";
    return cmmnd + dest.toString() + ", " + op;
  }
}

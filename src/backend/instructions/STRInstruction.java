package backend.instructions;

import backend.Register;

public class STRInstruction extends Instruction {

  private final Register source;
  private final String memory;

  public STRInstruction(Register source, Register indexRegister) {
    this.source = source;
    this.memory = "[" + indexRegister.toString() + "]";
  }

  public STRInstruction(Register source, Register indexRegister,
                        int offset) {
    this.source = source;
    this.memory = "[" + indexRegister.toString() + ", #" +
            Integer.toString(offset) + "]";
  }

  @Override
  public String asString() {
    return "STR " + source.toString() + "," + memory;
  }
}

package backend.instructions;

import backend.Register;

public class STRInstruction extends Instruction {

  private final Register source;
  private final String memory;
  private final boolean isSingleByte;

  public STRInstruction(Register source, Register indexRegister, boolean isSingleByte) {
    this.source = source;
    this.memory = "[" + indexRegister.toString() + "]";
    this.isSingleByte = isSingleByte;
  }

  public STRInstruction(Register source, Register indexRegister,
                        int offset, boolean isSingleByte) {
    this.source = source;
    this.memory = "[" + indexRegister.toString() + ", #" +
            Integer.toString(offset) + "]";
    this.isSingleByte = isSingleByte;
  }

  @Override
  public String asString() {
    String cmmnd = isSingleByte ? ("STRB ") : ("STR ");
    return cmmnd + source.toString() + "," + memory;
  }
}

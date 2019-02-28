package backend.instructions;

import backend.Register;

public class STRInstruction extends Instruction {

  private final Register source;
  private String memory;
  private final boolean isSingleByte;

  public STRInstruction(Register source, Register indexRegister, boolean isSingleByte) {
    this.source = source;
    this.memory = "[" + indexRegister.toString() + "]";
    this.isSingleByte = isSingleByte;
  }

  public STRInstruction(Register source, Register indexRegister,
                        int offset, boolean isSingleByte) {
    this(source, indexRegister, isSingleByte);

    if (offset != 0) {
      memory = "[" + indexRegister.toString() + ", #" +
              Integer.toString(offset) + "]";
    }
  }

  @Override
  public String asString() {
    String cmmnd = isSingleByte ? ("STRB ") : ("STR ");
    return cmmnd + source.toString() + "," + memory;
  }
}

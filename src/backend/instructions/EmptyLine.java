package backend.instructions;

public class EmptyLine extends Instruction {
  public EmptyLine() {}

  @Override
  public String asString() {
    return "";
  }
}

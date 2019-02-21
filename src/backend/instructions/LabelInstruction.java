package backend.instructions;

public class LabelInstruction extends Instruction {

  private String label;

  public LabelInstruction(String label) {
    this.label = label;
  }

  @Override
  public String asString() {
    return label + ":";
  }
}

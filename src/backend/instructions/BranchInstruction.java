package backend.instructions;

import backend.Condition;

public class BranchInstruction extends Instruction {
  private final Condition condition;
  private final String label;

  public BranchInstruction(Condition condition, String label) {
    this.condition = condition;
    this.label = label;
  }

  @Override
  public String asString() {
    return "B" + condition.name() + " " + label;
  }
}

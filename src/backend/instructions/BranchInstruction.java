package backend.instructions;

import backend.Condition;

import java.util.ArrayList;
import java.util.List;

public class BranchInstruction extends Instruction {
  private final List<Condition> conditions;
  private final String label;

  public BranchInstruction(Condition condition, String label) {
    this.conditions = new ArrayList<>();
    this.conditions.add(condition);
    this.label = label;
  }

  public BranchInstruction(List<Condition> conditions, String label){
    this.conditions = conditions;
    this.label = label;
  }

  @Override
  public String asString() {
    StringBuilder sb = new StringBuilder("B");
    conditions.forEach(sb::append);
    return sb.toString() + " " + label;
  }
}

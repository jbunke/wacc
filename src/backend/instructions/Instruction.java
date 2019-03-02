package backend.instructions;

public abstract class Instruction {

  abstract public String asString();

  public String getIndent() {
    return "\t\t";
  }

  @Override
  public String toString() {
    return asString();
  }
}

package backend.instructions;

public class Directive extends Instruction {
  private ID did;
  private String argument;

  public Directive(ID did) {
    assert (did == ID.LTORG || did == ID.DATA || did == ID.TEXT);
    this.did = did;
    this.argument = "";
  }

  public Directive(ID did, String argument) {
    this.did = did;
    this.argument = argument;
  }

  public enum ID {
    GLOBAL, DATA, TEXT, WORD, ASCII, LTORG
  }

  @Override
  public String asString() {
    if (argument.equals("")) return "." + did.name().toLowerCase();
    return "." + did.name().toLowerCase() + " " + argument;
  }
}

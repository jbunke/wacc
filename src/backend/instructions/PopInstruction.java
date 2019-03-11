package backend.instructions;

import backend.Register;

public class PopInstruction extends Instruction {

  private Register register;

  public PopInstruction(Register register) {
    this.register = register;
  }

  public Register getRegister() {
    return register;
  }

  @Override
  public String asString() {
    return "POP {" + register.toString() + "}";
  }
}

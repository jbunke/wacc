package backend.instructions;

import backend.Register;

public class PushInstruction extends Instruction {

  private Register register;

  public PushInstruction(Register register) {
    this.register = register;
  }

  public Register getRegister() {
    return register;
  }

  @Override
  public String asString() {
    return "PUSH {" + register.toString() + "}";
  }
}

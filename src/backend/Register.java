package backend;

import java.util.Stack;

public class Register {

  private ID regID;

  public Register(ID regID) {
    this.regID = regID;
  }

  public ID getRegID() {
    return regID;
  }

  public enum ID {
    R0, R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, SP, LR, PC
  }

  @Override
  public String toString() {
    return regID.name().toLowerCase();
  }

  static Stack<Register.ID> generalPurposeRegisters() {
    Stack<Register.ID> genRegs = new Stack<>();

    genRegs.push(ID.R12);
    genRegs.push(ID.R11);
    genRegs.push(ID.R10);
    genRegs.push(ID.R9);
    genRegs.push(ID.R8);
    genRegs.push(ID.R7);
    genRegs.push(ID.R6);
    genRegs.push(ID.R5);
    genRegs.push(ID.R4);

    return genRegs;
  }
}

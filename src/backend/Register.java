package backend;

public class Register {

  private ID regID;

  public Register(ID regID) {
    this.regID = regID;
  }

  public ID getRegID() {
    return regID;
  }

  public enum ID {
    R0, R1, R2, R3, R4, R5 ,R6, R7, R8, R9, R10, R11, R12, R13, LR, PC
  }
}

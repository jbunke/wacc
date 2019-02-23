package backend.instructions;

import backend.Register;

public class LDRInstruction extends Instruction{

    private Register destRegister;
    private int constant;

    public LDRInstruction(Register destRegisterId, int constant) {
        this.destRegister = destRegisterId;
        this.constant = constant;
    }

    @Override
    public String asString() {
        return "LDR " + destRegister.toString() +", =" + constant;
    }
}

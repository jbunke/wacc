package backend.instructions;

public class GlobalMainDirectiveInstruction extends Instruction{

    @Override
    public String asString() {
        return ".global main";
    }
}

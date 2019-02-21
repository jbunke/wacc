package backend.instructions;

public class TextDirectiveInstruction extends Instruction{

    @Override
    public String asString() {
        return ".text";
    }
}

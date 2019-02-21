package backend;

import backend.instructions.Instruction;
import backend.instructions.PopInstruction;
import backend.instructions.PushInstruction;
import frontend.abstractSyntaxTree.ProgramNode;
import frontend.abstractSyntaxTree.statements.StatementListNode;
import jdk.nashorn.internal.ir.FunctionNode;

import java.util.List;

public class AssemblyGeneratorVisitor {
    Register lrRegister, pcRegister;

    public AssemblyGeneratorVisitor(ProgramNode programNode) {
        pcRegister = new Register(Register.ID.PC);
        lrRegister = new Register(Register.ID.LR);
    }

    private List<Instruction> globalMainInstructions;

    private void generateCode() {
        // TODO
    }

    public String getGeneratedCode() {
        generateCode();

        StringBuilder stringBuilder = new StringBuilder();
        for (Instruction instr : globalMainInstructions) {
            stringBuilder.append(instr);
        }
        return stringBuilder.toString();
    }

    public void generateCode(FunctionNode functionNode) {
        globalMainInstructions.add(new PushInstruction(lrRegister));

        globalMainInstructions.add(new PopInstruction(pcRegister));
    }

    private void generateCode(StatementListNode statementListNode) {

    }


}

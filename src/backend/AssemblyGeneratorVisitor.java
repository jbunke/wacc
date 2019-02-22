package backend;

import backend.instructions.*;
import frontend.abstractSyntaxTree.ProgramNode;
import frontend.symbolTable.SymbolTable;

import java.io.*;
import java.util.*;

public class AssemblyGeneratorVisitor {
    private ProgramNode programNode;
    private SymbolTable symbolTable;
    private List<Instruction> globalMainInstructions;
    private Map<Register.ID, Register> registers;

    public AssemblyGeneratorVisitor(ProgramNode programNode,
                                    SymbolTable symbolTable) {
        this.programNode = programNode;
        this.symbolTable = symbolTable;
    }

    public void writeGeneratedCode(File file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(new TextDirectiveInstruction());
        stringBuilder.append(new GlobalMainDirectiveInstruction());

        generateCode();

        for (Instruction instr : globalMainInstructions) {
            stringBuilder.append(instr);
            stringBuilder.append("\n");
        }
        String code = stringBuilder.toString();

        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter br = new BufferedWriter(fileWriter);
        br.write(code);
        br.close();
        fileWriter.close();
    }

    public Register getRegister(Register.ID registerId){
        return registers.get(registerId);
    }

    private void generateCode() {
        setupRegisters();
        globalMainInstructions.addAll(
                programNode.generateAssembly(this, symbolTable));
    }

    private Map<Register.ID, Register> setupRegisters() {
        registers = new HashMap<>();

        for (Register.ID id : Register.ID.values()) {
            registers.put(id, new Register(id));
        }
        return registers;
    }

}

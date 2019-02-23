package backend;

import backend.instructions.*;
import frontend.abstractSyntaxTree.ProgramNode;
import frontend.symbolTable.SymbolTable;

import java.io.*;
import java.util.*;

public class AssemblyGeneratorVisitor {
  private ProgramNode programNode;
  private SymbolTable symbolTable;
  private List<Instruction> instructions;
  private Map<Register.ID, Register> registers;
  private Set<String> labels;

  public AssemblyGeneratorVisitor(ProgramNode programNode,
                                  SymbolTable symbolTable) {
    this.programNode = programNode;
    this.symbolTable = symbolTable;

    setupRegisters();
    this.labels = Set.of("main");
  }

  public void generateAssembly(File file) throws IOException {
    generateAssembly();
    writeToFile(file);
  }

  private void generateAssembly() {
    instructions = new ArrayList<>();

    // Pre-main assembly code:
    instructions.add(new Directive(Directive.ID.TEXT));
    instructions.add(new Directive(Directive.ID.GLOBAL, "main"));
    instructions.add(new LabelInstruction("main"));

    List<Instruction> mainInstr =
            programNode.generateAssembly(this, symbolTable,
                    Register.generalPurposeRegisters());

    instructions.addAll(mainInstr);
  }

  private void writeToFile(File file) throws IOException {
    StringBuilder sbProg = new StringBuilder();

    for (Instruction instruction : instructions) {
      sbProg.append(instruction.asString());
      sbProg.append("\n");
    }

    String program = sbProg.toString();

    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    writer.write(program);
    writer.close();
  }

  public Register getRegister(Register.ID registerId) {
    return registers.get(registerId);
  }

  private void setupRegisters() {
    registers = new HashMap<>();

    for (Register.ID id : Register.ID.values()) {
      registers.put(id, new Register(id));
    }
  }

}

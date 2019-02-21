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

  private void generateCode() {
    Map<Register.ID, Register> registers = new HashMap<>();

    for (Register.ID id : Register.ID.values()) {
      registers.put(id, new Register(id));
    }

    globalMainInstructions.addAll(
            programNode.generateAssembly(registers, symbolTable));
  }

}

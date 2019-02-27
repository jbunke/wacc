package backend;

import backend.instructions.*;
import frontend.abstractSyntaxTree.ProgramNode;
import frontend.symbolTable.SymbolTable;

import java.io.*;
import java.util.*;

public class AssemblyGenerator {
  private ProgramNode programNode;
  private SymbolTable symbolTable;
  private List<Instruction> instructions;
  private List<Instruction> additionals;
  private Map<Register.ID, Register> registers;
  private Set<String> labels;
  private List<Instruction> dataDirectives;

  public AssemblyGenerator(ProgramNode programNode,
                           SymbolTable symbolTable) {
    this.programNode = programNode;
    this.symbolTable = symbolTable;

    setupRegisters();
    this.labels = new HashSet<>();
    labels.add("main");
    this.dataDirectives = new ArrayList<>();
    this.additionals = new ArrayList<>();
  }

  public void generateAssembly(File file) throws IOException {
    generateAssembly();
    writeToFile(file);
  }

  private void generateAssembly() {
    instructions = new ArrayList<>();

    // Pre-main assembly code:
    instructions.add(new Directive(Directive.ID.TEXT));
    instructions.add(new EmptyLine());
    instructions.add(new Directive(Directive.ID.GLOBAL, "main"));
    instructions.add(new LabelInstruction("main"));

    List<Instruction> mainInstr =
            programNode.generateAssembly(this, symbolTable,
                    Register.generalPurposeRegisters());

    if (!dataDirectives.isEmpty()) {
      dataDirectives.add(new EmptyLine());
    }

    instructions.addAll(mainInstr);

    instructions.addAll(additionals);
  }

  private void writeToFile(File file) throws IOException {
    StringBuilder sbProg = new StringBuilder();

    for (Instruction dataDirective : dataDirectives) {
      sbProg.append(dataDirective.getIndent());
      sbProg.append(dataDirective.asString());
      sbProg.append("\n");
    }

    for (Instruction instruction : instructions) {
      sbProg.append(instruction.getIndent());
      sbProg.append(instruction.asString());
      sbProg.append("\n");
    }

    String program = sbProg.toString();

    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    writer.write(program);
    writer.close();

    System.out.println(program);
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

  public String addMsg(String toAdd) {
    if (dataDirectives.isEmpty()) {
      dataDirectives.add(new Directive(Directive.ID.DATA));
      dataDirectives.add(new EmptyLine());
    }

    String label = "msg_" + (dataDirectives.size() / 3);
    dataDirectives.add(new LabelInstruction(label));
    dataDirectives.add(new Directive(Directive.ID.WORD,
            Integer.toString(realStringLength(toAdd))));
    dataDirectives.add(new Directive(Directive.ID.ASCII, toAdd));

    return label;
  }

  public void addAdditional(String label, List<Instruction> additionals) {
    this.labels.add(label);
    this.additionals.add(new LabelInstruction(label));
    this.additionals.addAll(additionals);
  }

  public boolean containsLabel(String label) {
    return labels.contains(label);
  }

  /* Checks for escaped characters and counts them as having a
   * length of 1 instead of two */
  private int realStringLength(String s) {
    int baseLength = s.length();
    int subtract = 0;
    boolean lastEscape = false;
    for (char c : s.toCharArray()) {
      if (c == '\\') {
        if (lastEscape) {
          lastEscape = false;
        } else {
          subtract++;
          lastEscape = true;
        }
      }
    }
    return baseLength - subtract;
  }
}

package backend;

import backend.instructions.*;
import frontend.abstractSyntaxTree.ProgramNode;
import frontend.abstractSyntaxTree.statements.PrintStatementNode;
import frontend.symbolTable.SymbolTable;

import java.io.*;
import java.util.*;
import java.util.function.BiFunction;

public class AssemblyGenerator {
  private static final String TERMIN_STRING = "%.*s\\0";

  private ProgramNode programNode;
  private SymbolTable symbolTable;
  private List<Instruction> instructions;
  private List<Instruction> additionals;
  private Map<Register.ID, Register> registers;
  private Set<String> labels;
  private List<Instruction> dataDirectives;

  private int ifCount;

  public AssemblyGenerator(ProgramNode programNode,
                           SymbolTable symbolTable) {
    this.programNode = programNode;
    this.symbolTable = symbolTable;

    setupRegisters();
    this.labels = new HashSet<>();
    labels.add("main");
    this.dataDirectives = new ArrayList<>();
    this.additionals = new ArrayList<>();

    this.ifCount = 0;
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

  public String generateIfDeallocSP(SymbolTable symbolTable, String falseLabel) {
    String label = "L" + Integer.toString(
            Integer.parseInt(falseLabel.substring(1)) + 1);
    List<Instruction> instructions = new ArrayList<>();
    if (label.equals("L1")) {
      if (symbolTable.getSize() > 0) {
        instructions.add(ArithInstruction.add(getRegister(Register.ID.SP),
                getRegister(Register.ID.SP), symbolTable.getSize()));
      }
      instructions.add(new LDRInstruction(getRegister(Register.ID.R0), 0));
      instructions.add(new PopInstruction(getRegister(Register.ID.PC)));
      instructions.add(new Directive(Directive.ID.LTORG));
    } else {
      instructions.add(new BranchInstruction(new ArrayList<>(), "L1"));
    }
    addAdditional(label, instructions);
    return label;
  }

  public String generateIfBranch(List<Instruction> instructions) {
    String label = "L" + Integer.toString(ifCount * 2);
    ifCount++;
    addAdditional(label, instructions);
    return label;
  }

  public void generateLabel(String label,
          String[] msgs, BiFunction<AssemblyGenerator,
          String[],List<Instruction>> function) {
    if (!containsLabel(label)) {
      String[] retMsgs = new String[msgs.length];
      for (int i = 0; i < msgs.length; i++) {
        retMsgs[i] = addMsg(msgs[i]);
      }
      addAdditional(label, function.apply(this, retMsgs));
    }
  }

  public static List<Instruction> throw_overflow_error(AssemblyGenerator generator,
                                                String[] msgs) {
    List<Instruction> instructions = new ArrayList<>();
    instructions.add(new LDRInstruction(
            generator.getRegister(Register.ID.R0), msgs[0]));
    generator.generateLabel("p_throw_runtime_error", new String[0],
            AssemblyGenerator::throw_runtime_error);
    instructions.add(
            new BranchInstruction(Condition.L, "p_throw_runtime_error"));
    return instructions;
  }

  private static List<Instruction> throw_runtime_error(AssemblyGenerator generator,
                                               String[] msgs) {
    List<Instruction> instructions = new ArrayList<>();
    generator.generateLabel("p_print_string", new String[] {TERMIN_STRING},
            PrintStatementNode::print_string);
    instructions.add(new BranchInstruction(Condition.L, "p_print_string"));
    instructions.add(new MovInstruction(generator.getRegister(Register.ID.R0),
            -1));
    instructions.add(new BranchInstruction(Condition.L, "exit"));
    return instructions;
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

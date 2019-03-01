package backend;

import backend.Register.ID;
import backend.instructions.*;
import frontend.abstractSyntaxTree.ProgramNode;
import frontend.abstractSyntaxTree.statements.PrintStatementNode;
import frontend.abstractSyntaxTree.statements.StatementNode;
import frontend.symbolTable.SymbolTable;
import java.io.*;
import java.util.*;
import java.util.function.BiFunction;

public class AssemblyGenerator {
  private static final String TERMIN_STRING = "%.*s\\0";
  private static final int NULL_PTR_VAL = 0;
  private static final int ADDR_SIZE = 4;

  private ProgramNode programNode;
  private SymbolTable symbolTable;
  private Map<String, List<Instruction>> instructions;
  private List<Instruction> additionals;
  private Map<Register.ID, Register> registers;
  private List<String> labels;
  private List<Instruction> dataDirectives;

  private int lLabelCount;
  private String activeLabel;
  private Stack<String> lastLabels;

  public AssemblyGenerator(ProgramNode programNode,
                           SymbolTable symbolTable) {
    this.programNode = programNode;
    this.symbolTable = symbolTable;

    setupRegisters();
    this.labels = new ArrayList<>();
    labels.add("main");
    this.dataDirectives = new ArrayList<>();
    this.additionals = new ArrayList<>();

    this.lLabelCount = 0;
    this.lastLabels = new Stack<>();
  }

  /**
   * generateAssembly is called from WACCCompiler (the main class)
   * the assembly generation is done in the private generateAssembly().
   * File writing is done in writeToFile(). */
  public void generateAssembly(File file) throws IOException {
    generateAssembly();
    writeToFile(file);
  }

  private void generateAssembly() {
    instructions = new HashMap<>();
    // Pre-main assembly
    List<Instruction> programInstructions = new ArrayList<>();
    programInstructions.add(new Directive(Directive.ID.TEXT));
    programInstructions.add(new EmptyLine());
    programInstructions.add(new Directive(Directive.ID.GLOBAL, "main"));

    instructions.put("_PROGRAM_", programInstructions);

    // programInstructions.add(new LabelInstruction("main"));
    setActiveLabel("main");
    programNode.generateAssembly(this, symbolTable,
                    Register.generalPurposeRegisters());

    if (!dataDirectives.isEmpty()) {
      dataDirectives.add(new EmptyLine());
    }
  }

  private void writeToFile(File file) throws IOException {
    StringBuilder sbProg = new StringBuilder();

    for (Instruction dataDirective : dataDirectives) {
      sbProg.append(dataDirective.getIndent());
      sbProg.append(dataDirective.asString());
      sbProg.append("\n");
    }

    List<Instruction> programInstructions = instructions.get("_PROGRAM_");
    for (Instruction instruction : programInstructions) {
      sbProg.append(instruction.getIndent());
      sbProg.append(instruction.asString());
      sbProg.append("\n");
    }

    for (String label : labels) {
      if (instructions.containsKey(label)) {
        List<Instruction> instructionList = instructions.get(label);
        for (Instruction instruction : instructionList) {
          sbProg.append(instruction.getIndent());
          sbProg.append(instruction.asString());
          sbProg.append("\n");
        }
      }
    }

    for (Instruction additional : additionals) {
      sbProg.append(additional.getIndent());
      sbProg.append(additional.asString());
      sbProg.append("\n");
    }

    String program = sbProg.toString();

    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    writer.write(program);
    writer.close();
  }

  /** allocate and deallocate handle the (de)allocation
   * of stack space whenever a scope change occurs */
  public void allocate(SymbolTable symbolTable) {
    int size = symbolTable.getSize();

    while (size > 0) {
      addInstruction(
              ArithInstruction.sub(getRegister(Register.ID.SP),
              getRegister(Register.ID.SP), Math.min(1024, size)));
      size = Math.max(0, size - 1024);
    }
  }

  public void deallocate(SymbolTable symbolTable) {
    int size = symbolTable.getSize();

    while (size > 0) {
      addInstruction(
              ArithInstruction.add(getRegister(Register.ID.SP),
              getRegister(Register.ID.SP), Math.min(1024, size)));
      size = Math.max(0, size - 1024);
    }
  }

  /**
   * addInstruction adds a specified instruction to
   * the instruction list of the active label
   *
   * @param instruction the instruction to be added */
  public void addInstruction(Instruction instruction) {
    if (!instructions.containsKey(activeLabel)) {
      instructions.put(activeLabel, new ArrayList<>());
    }
    List<Instruction> instructionList = instructions.get(activeLabel);
    instructionList.add(instruction);
  }

  public void sortLabels(String first, String second) {
    if (labels.indexOf(first) > labels.indexOf(second)) {
      labels.remove(first);
      labels.add(labels.indexOf(second), first);
    }
  }

  public void setActiveLabel(String activeLabel) {
    lastLabels.push(this.activeLabel);
    this.activeLabel = activeLabel;
    if (!labels.contains(activeLabel)) {
      labels.add(0, activeLabel);
    }
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

  /**
   * Generates a unique new label used for conditionals and loops */
  public String generateNewLabel() {
    String label = "L" + Integer.toString(lLabelCount);
    lLabelCount++;
    labels.add(label);
    return label;
  }

  /**
   * Generates helper functions
   * @param msgs Data directives for helper functions
   * @param function Function to call to generate instructions */
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
    generator.generateLabel("p_throw_runtime_error",
            new String[0],
            AssemblyGenerator::throw_runtime_error);
    instructions.add(
            new BranchInstruction(Condition.L, "p_throw_runtime_error"));
    return instructions;
  }

  public static List<Instruction> check_null_ptr(AssemblyGenerator generator,
      String[] msgs) {
    List<Instruction> instructions = new ArrayList<>();
    Register R0 = generator.getRegister(ID.R0);

    instructions.add(new PushInstruction(generator.getRegister(ID.LR)));
    instructions.add(new CompareInstruction(R0, NULL_PTR_VAL));
    instructions.add(new LDRInstruction(Condition.EQ, R0, msgs[0]));

    generator.generateLabel("p_throw_runtime_error",
        new String[0],
        AssemblyGenerator::throw_runtime_error);

    instructions.add(new BranchInstruction(List.of(Condition.L, Condition.EQ),
        "p_throw_runtime_error"));

    instructions.add(new PopInstruction(generator.getRegister(ID.PC)));

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

  public static List<Instruction> check_divide_by_zero(AssemblyGenerator generator,
                                                        String[] msgName){
    Register r0 = generator.getRegister(Register.ID.R0);
    Register r1 = generator.getRegister(Register.ID.R1);

    List<Instruction> instructions = new ArrayList<>();
    instructions.add(new PushInstruction(generator.getRegister(Register.ID.LR)));
    instructions.add(new CompareInstruction(r1, 0));
    instructions.add(new LDRInstruction(r0, msgName[0])
            .withCondition(Condition.EQ));
    List<Condition> branchConds = List.of(Condition.L, Condition.EQ);
    instructions.add(new BranchInstruction(branchConds,
            "p_throw_runtime_error"));
    instructions.add(new PopInstruction(generator.getRegister(Register.ID.PC)));

    generator.generateLabel("p_throw_runtime_error", new String[0],AssemblyGenerator::throw_runtime_error);

    return instructions;
  }

  public static List<Instruction> free_pair(AssemblyGenerator generator, String[] msgs) {
    List<Instruction> instructions = new ArrayList<>();
    Register LinkReg = generator.getRegister(ID.LR);
    Register R0 = generator.getRegister(ID.R0);
    Register SP = generator.getRegister(ID.SP);
    Register PC = generator.getRegister(ID.PC);

    instructions.add(new PushInstruction(LinkReg));

    instructions.add(new CompareInstruction(R0, NULL_PTR_VAL));
    instructions.add(new LDRInstruction(Condition.EQ, R0, msgs[0]));

    generator.generateLabel("p_throw_runtime_error",
            new String[0],
            AssemblyGenerator::throw_runtime_error);


    instructions.add(new BranchInstruction(Condition.EQ,
            "p_throw_runtime_error"));

    instructions.add(new PushInstruction(R0));

    instructions.add(new LDRInstruction(R0, R0));
    instructions.add(new BranchInstruction(Condition.L, "free"));

    instructions.add(new LDRInstruction(R0, SP));
    instructions.add(new LDRInstruction(R0, R0, ADDR_SIZE));
    instructions.add(new BranchInstruction(Condition.L, "free"));

    instructions.add(new PopInstruction(R0));
    instructions.add(new BranchInstruction(Condition.L, "free"));
    instructions.add(new PopInstruction(PC));

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
    for (char c : s.toCharArray()) {
      if (c == '\\') {
        subtract++;
      }
    }
    return baseLength - subtract;
  }
}

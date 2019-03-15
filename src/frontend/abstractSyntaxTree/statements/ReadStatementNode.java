package frontend.abstractSyntaxTree.statements;

import backend.AssemblyGenerator;
import backend.Condition;
import backend.Register;
import backend.instructions.*;
import frontend.abstractSyntaxTree.assignment.AssignLHS;
import frontend.abstractSyntaxTree.expressions.IdentifierNode;
import frontend.symbolTable.*;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;
import shell.structural.Heap;
import shell.structural.ShellStatementControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class ReadStatementNode extends StatementNode {
  private static final String INT_FORMATTER = "%d\\0";
  private static final String CHAR_FORMATTER = "%c\\0";

  private final AssignLHS lhs;

  public ReadStatementNode(AssignLHS lhs) {
    this.lhs = lhs;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    if (!lhs.getType(symbolTable).equals(new BaseTypes(BaseTypes.base_types.CHAR)) && !lhs.getType(symbolTable)
            .equals(new BaseTypes(
                    BaseTypes
                            .base_types.INT))) {
      errorList.addError(new SemanticError("Standard input only allows character or integer "
              + "Variable given: \""
              + lhs.getType(symbolTable).toString()
              + "\"."));
    }
    lhs.semanticCheck(symbolTable, errorList);
  }

  @Override
  public void generateAssembly(AssemblyGenerator generator,
                               SymbolTable symbolTable,
                               Stack<Register.ID> available) {
    Register first = generator.getRegister(available.peek());
    if (lhs instanceof IdentifierNode) {
      String identifier = ((IdentifierNode) lhs).getName();
      generator.addInstruction(ArithInstruction.add(first,
              generator.getRegister(Register.ID.SP),
              symbolTable.fetchOffset(identifier)));
    } else {
      lhs.generateAssembly(generator, symbolTable, available);
      generator.addInstruction(ArithInstruction.add(first,
              generator.getRegister(Register.ID.SP), 0));
    }
    generator.addInstruction(new MovInstruction(
            generator.getRegister(Register.ID.R0), first));

    if (lhs.getType(symbolTable) instanceof BaseTypes) {
      BaseTypes expType = (BaseTypes) lhs.getType(symbolTable);
      switch (expType.getBaseType()) {
        case INT:
          if (!generator.containsLabel("p_read_int")) {
            String intMsg = generator.addMsg(INT_FORMATTER);
            generator.addAdditional("p_read_int",
                    read(generator, intMsg));
          }
          generator.addInstruction(
                  new BranchInstruction(Condition.L, "p_read_int"));
          break;
        case CHAR:
          if (!generator.containsLabel("p_read_char")) {
            String charMsg = generator.addMsg(CHAR_FORMATTER);
            generator.addAdditional("p_read_char",
                    read(generator, charMsg));
          }
          generator.addInstruction(
                  new BranchInstruction(Condition.L, "p_read_char"));
          break;
      }
    }
  }

  private static List<Instruction> read(AssemblyGenerator generator,
                                        String intMsg) {
    List<Instruction> instructions = new ArrayList<>();
    instructions.add(new PushInstruction(generator.getRegister(Register.ID.LR)));
    instructions.add(new MovInstruction(generator.getRegister(Register.ID.R1),
            generator.getRegister(Register.ID.R0)));
    instructions.add(new LDRInstruction(generator.getRegister(Register.ID.R0),
            intMsg));
    instructions.add(ArithInstruction.add(
            generator.getRegister(Register.ID.R0),
            generator.getRegister(Register.ID.R0), 4));
    instructions.add(new BranchInstruction(Condition.L, "scanf"));
    instructions.add(new PopInstruction(generator.getRegister(Register.ID.PC)));
    return instructions;
  }

  @Override
  public String toString() {
    return "read " + lhs.toString();
  }

  @Override
  public ShellStatementControl applyStatement(SymbolTable symbolTable,
      Heap heap) {
    Scanner in = new Scanner(System.in);

    String input = in.nextLine();
    input = input.trim();

    if (lhs instanceof IdentifierNode) {
      IdentifierNode identifier = (IdentifierNode) lhs;
      Type type = identifier.getType(symbolTable);
      if (type instanceof BaseTypes) {
        BaseTypes baseType = (BaseTypes) type;
        switch (baseType.getBaseType()) {
          case INT:
            symbolTable.setValue(identifier.getName(),
                    Integer.parseInt(input));
            break;
          case BOOL:
            symbolTable.setValue(identifier.getName(),
                    Boolean.parseBoolean(input));
            break;
          case CHAR:
            symbolTable.setValue(identifier.getName(), input.charAt(0));
            break;
        }
      } else {
        // TODO: What if isn't string? Will break
        symbolTable.setValue(identifier.getName(), input);
      }
    }

    return ShellStatementControl.cont();
  }
}

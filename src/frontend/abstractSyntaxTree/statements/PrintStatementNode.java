package frontend.abstractSyntaxTree.statements;

import backend.AssemblyGenerator;
import backend.Condition;
import backend.Register;
import backend.instructions.*;
import frontend.abstractSyntaxTree.expressions.ExpressionNode;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Array;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Pair;
import shell.ShellStatementControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PrintStatementNode extends StatementNode {
  private static final String TERMIN_STRING = "%.*s\\0";
  private static final String TRUE_STRING = "true\\0";
  private static final String FALSE_STRING = "false\\0";
  private static final String INT_FORMATTER = "%d\\0";
  private static final String PAIR_FORMATTER = "%p\\0";

  private final ExpressionNode expression;

  public PrintStatementNode(ExpressionNode expr) {
    this.expression = expr;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    expression.semanticCheck(symbolTable, errorList);

    if (expression.getType(symbolTable) == null) {
      errorList.addError(new SemanticError("Type of expression given in \"print\" statement could not be resolved."));
    }
  }

  @Override
  public void generateAssembly(AssemblyGenerator generator,
                               SymbolTable symbolTable,
                               Stack<Register.ID> available) {
    expression.generateAssembly(generator,
            symbolTable, available);
    Register first = generator.getRegister(available.peek());
    generator.addInstruction(
            new MovInstruction(generator.getRegister(Register.ID.R0), first));

    if (expression.getType(symbolTable) instanceof BaseTypes) {
      BaseTypes expType = (BaseTypes) expression.getType(symbolTable);
      switch (expType.getBaseType()) {
        case INT:
          generator.generateLabel("p_print_int",
                  new String[]{INT_FORMATTER}, PrintStatementNode::print_int);
          generator.addInstruction(
                  new BranchInstruction(Condition.L, "p_print_int"));
          break;
        case BOOL:
          generator.generateLabel("p_print_bool",
                  new String[]{TRUE_STRING, FALSE_STRING},
                  PrintStatementNode::print_bool);
          generator.addInstruction(
                  new BranchInstruction(Condition.L, "p_print_bool"));
          break;
        case CHAR:
          generator.addInstruction(
                  new BranchInstruction(Condition.L, "putchar"));
          break;
      }
    } else if (expression.getType(symbolTable) instanceof Array) {
      Array expType = (Array) expression.getType(symbolTable);
      if (expType.getElementType() instanceof BaseTypes &&
              ((BaseTypes) expType.getElementType()).getBaseType() ==
                      BaseTypes.base_types.CHAR) {
        // Dealing with a string
        generator.generateLabel("p_print_string",
                new String[]{TERMIN_STRING},
                PrintStatementNode::print_string);
        generator.addInstruction(
                new BranchInstruction(Condition.L, "p_print_string"));
      } else {
        generator.generateLabel("p_print_reference",
                new String[]{PAIR_FORMATTER},
                PrintStatementNode::print_int);
        generator.addInstruction(new BranchInstruction(
                Condition.L, "p_print_reference"));
      }
    } else if (expression.getType(symbolTable) instanceof Pair ||
            expression.getType(symbolTable) instanceof Array) {
      generator.generateLabel("p_print_reference",
              new String[]{PAIR_FORMATTER},
              PrintStatementNode::print_int);
      generator.addInstruction(new BranchInstruction(
              Condition.L, "p_print_reference"));
    }
  }

  public static List<Instruction> print_string(AssemblyGenerator generator,
                                               String[] msgs) {
    List<Instruction> instructions = new ArrayList<>();
    instructions.add(new PushInstruction(generator.getRegister(Register.ID.LR)));
    instructions.add(new LDRInstruction(generator.getRegister(Register.ID.R1),
            generator.getRegister(Register.ID.R0)));
    instructions.add(ArithInstruction.add(generator.getRegister(Register.ID.R2),
            generator.getRegister(Register.ID.R0), 4));
    instructions.add(new LDRInstruction(generator.getRegister(Register.ID.R0),
            msgs[0]));
    instructions.add(ArithInstruction.add(generator.getRegister(Register.ID.R0),
            generator.getRegister(Register.ID.R0), 4));
    instructions.add(new BranchInstruction(Condition.L, "printf"));
    instructions.add(new MovInstruction(
            generator.getRegister(Register.ID.R0), 0));
    instructions.add(new BranchInstruction(Condition.L, "fflush"));
    instructions.add(new PopInstruction(generator.getRegister(Register.ID.PC)));
    return instructions;
  }

  private static List<Instruction> print_bool(AssemblyGenerator generator,
                                              String[] msgs) {
    List<Instruction> instructions = new ArrayList<>();
    instructions.add(new PushInstruction(generator.getRegister(Register.ID.LR)));
    instructions.add(new CompareInstruction(
            generator.getRegister(Register.ID.R0), 0));
    instructions.add(new LDRInstruction(Condition.NE,
            generator.getRegister(Register.ID.R0), msgs[0]));
    instructions.add(new LDRInstruction(Condition.EQ,
            generator.getRegister(Register.ID.R0), msgs[1]));
    instructions.add(ArithInstruction.add(
            generator.getRegister(Register.ID.R0),
            generator.getRegister(Register.ID.R0), 4));
    instructions.add(new BranchInstruction(Condition.L, "printf"));
    instructions.add(new MovInstruction(
            generator.getRegister(Register.ID.R0), 0));
    instructions.add(new BranchInstruction(Condition.L, "fflush"));
    instructions.add(new PopInstruction(generator.getRegister(Register.ID.PC)));
    return instructions;
  }

  private static List<Instruction> print_int(AssemblyGenerator generator,
                                             String[] intMsg) {
    List<Instruction> instructions = new ArrayList<>();
    instructions.add(new PushInstruction(generator.getRegister(Register.ID.LR)));
    instructions.add(new MovInstruction(generator.getRegister(Register.ID.R1),
            generator.getRegister(Register.ID.R0)));
    instructions.add(new LDRInstruction(generator.getRegister(Register.ID.R0),
            intMsg[0]));
    instructions.add(ArithInstruction.add(
            generator.getRegister(Register.ID.R0),
            generator.getRegister(Register.ID.R0), 4));
    instructions.add(new BranchInstruction(Condition.L, "printf"));
    instructions.add(new MovInstruction(
            generator.getRegister(Register.ID.R0), 0));
    instructions.add(new BranchInstruction(Condition.L, "fflush"));
    instructions.add(new PopInstruction(generator.getRegister(Register.ID.PC)));
    return instructions;
  }

  @Override
  public String toString() {
    return "print " + expression.toString();
  }

  @Override
  public ShellStatementControl applyStatement(SymbolTable symbolTable) {
    System.out.print(expression.evaluate(symbolTable));
    return ShellStatementControl.cont();
  }
}

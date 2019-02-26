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

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PrintStatementNode extends StatementNode {
  private static final String TERMIN_STRING = "%.*s\\0";
  private static final String TRUE_STRING = "true\\0";
  private static final String FALSE_STRING = "false\\0";

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
  public List<Instruction> generateAssembly(AssemblyGenerator generator,
                                            SymbolTable symbolTable,
                                            Stack<Register.ID> available) {
    List<Instruction> instructions = expression.generateAssembly(generator,
            symbolTable, available);
    Register first = generator.getRegister(available.peek());
    instructions.add(new MovInstruction(generator.getRegister(Register.ID.R0),
            first));

    if (expression.getType(symbolTable) instanceof BaseTypes) {
      BaseTypes expType = (BaseTypes) expression.getType(symbolTable);
      switch (expType.getBaseType()) {
        case INT:
          break;
        case BOOL:
          if (!generator.containsLabel("p_print_bool")) {
            String trueMsg = generator.addMsg(TRUE_STRING);
            String falseMsg = generator.addMsg(FALSE_STRING);
            generator.addAdditional("p_print_bool",
                    print_bool(generator, trueMsg, falseMsg));
          }

          instructions.add(new BranchInstruction(Condition.L, "p_print_bool"));
          break;
        case CHAR:
          break;
      }
    } else if (expression.getType(symbolTable) instanceof Array) {
      Array expType = (Array) expression.getType(symbolTable);
      if (expType.getElementType() instanceof BaseTypes &&
              ((BaseTypes) expType.getElementType()).getBaseType() ==
                      BaseTypes.base_types.CHAR) {
        // Dealing with a string
        if (!generator.containsLabel("p_print_string")) {
          String code = generator.addMsg(TERMIN_STRING);
          generator.addAdditional("p_print_string",
                  print_string(generator, code));
        }

        instructions.add(new BranchInstruction(Condition.L, "p_print_string"));
      }
    }

    return instructions;
  }

  private static List<Instruction> print_string(AssemblyGenerator generator,
                                                String code) {
    List<Instruction> instructions = new ArrayList<>();
    instructions.add(new PushInstruction(generator.getRegister(Register.ID.LR)));
    instructions.add(new LDRInstruction(generator.getRegister(Register.ID.R1),
            generator.getRegister(Register.ID.R0)));
    instructions.add(new AddInstruction(generator.getRegister(Register.ID.R2),
            generator.getRegister(Register.ID.R0), 4));
    instructions.add(new LDRInstruction(generator.getRegister(Register.ID.R0),
            code));
    instructions.add(new AddInstruction(generator.getRegister(Register.ID.R0),
            generator.getRegister(Register.ID.R0), 4));
    instructions.add(new BranchInstruction(Condition.L, "printf"));
    instructions.add(new MovInstruction(
            generator.getRegister(Register.ID.R0), 0));
    instructions.add(new BranchInstruction(Condition.L, "fflush"));
    instructions.add(new PopInstruction(generator.getRegister(Register.ID.PC)));
    return instructions;
  }

  private static List<Instruction> print_bool(AssemblyGenerator generator,
                                              String trueMsg, String falseMsg) {
    List<Instruction> instructions = new ArrayList<>();
    instructions.add(new PushInstruction(generator.getRegister(Register.ID.LR)));
    instructions.add(new CompareInstruction(
            generator.getRegister(Register.ID.R0), 0));
    instructions.add(new LDRInstruction(Condition.NE,
            generator.getRegister(Register.ID.R0), trueMsg));
    instructions.add(new LDRInstruction(Condition.EQ,
            generator.getRegister(Register.ID.R0), falseMsg));
    instructions.add(new AddInstruction(
            generator.getRegister(Register.ID.R0),
            generator.getRegister(Register.ID.R0), 4));
    instructions.add(new BranchInstruction(Condition.L, "printf"));
    instructions.add(new MovInstruction(
            generator.getRegister(Register.ID.R0), 0));
    instructions.add(new BranchInstruction(Condition.L, "fflush"));
    instructions.add(new PopInstruction(generator.getRegister(Register.ID.PC)));
    return instructions;
  }
}

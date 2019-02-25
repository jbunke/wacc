package frontend.abstractSyntaxTree.statements;


import backend.AssemblyGenerator;
import backend.Condition;
import backend.Register;
import backend.instructions.*;
import frontend.abstractSyntaxTree.expressions.ExpressionNode;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PrintStatementNode extends StatementNode {
  private static final String TERMIN_STRING = "%.*s\\0";

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
    if (!generator.containsLabel("p_print_string")) {
      String code = generator.addMsg(TERMIN_STRING);
      generator.addAdditional("p_print_string",
              print_string(generator, code, available));
    }

    instructions.add(new BranchInstruction(Condition.L, "p_print_string"));
    return instructions;
  }

  private static List<Instruction> print_string(AssemblyGenerator generator,
                                                String code,
                                                Stack<Register.ID> available) {
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
}

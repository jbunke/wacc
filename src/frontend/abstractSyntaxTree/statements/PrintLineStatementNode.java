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

public class PrintLineStatementNode extends StatementNode {
  private final ExpressionNode expression;

  public PrintLineStatementNode(ExpressionNode expr) {
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
    new PrintStatementNode(expression).generateAssembly(
            generator, symbolTable, available);

    if (!generator.containsLabel("p_print_ln")) {
      String code = generator.addMsg("\\0");
      generator.addAdditional("p_print_ln",
              print_ln(generator, code));
    }

    generator.addInstruction(
            new BranchInstruction(Condition.L, "p_print_ln"));
  }

  private static List<Instruction> print_ln(
          AssemblyGenerator generator, String code) {
    boolean isSingleByte = code.length() == 1;
    List<Instruction> instructions = new ArrayList<>();
    instructions.add(new PushInstruction(generator.getRegister(Register.ID.LR)));
    instructions.add(new LDRInstruction(
            generator.getRegister(Register.ID.R0), code).isSingleByte(isSingleByte));
    instructions.add(ArithInstruction.add(generator.getRegister(Register.ID.R0),
            generator.getRegister(Register.ID.R0), 4));
    instructions.add(new BranchInstruction(Condition.L, "puts"));
    instructions.add(new MovInstruction(
            generator.getRegister(Register.ID.R0), 0));
    instructions.add(new BranchInstruction(Condition.L, "fflush"));
    instructions.add(new PopInstruction(generator.getRegister(Register.ID.PC)));
    return instructions;
  }

  @Override
  public String toString() {
    return "println " + expression.toString();
  }
}

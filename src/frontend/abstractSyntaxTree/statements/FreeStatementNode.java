package frontend.abstractSyntaxTree.statements;

import backend.AssemblyGenerator;
import backend.Condition;
import backend.Register;
import backend.Register.ID;
import backend.instructions.BranchInstruction;
import backend.instructions.MovInstruction;
import frontend.abstractSyntaxTree.expressions.ExpressionNode;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Array;
import frontend.symbolTable.types.Pair;
import frontend.symbolTable.types.Type;
import shell.Heap;
import shell.ShellStatementControl;

import java.util.Stack;

public class FreeStatementNode extends StatementNode {
  private static final String FREE_PAIR = "p_free_pair";
  private static final String NULL_FREE = "NullReferenceError: dereference a " +
          "null reference\\n\\0";

  private final ExpressionNode expression;

  public FreeStatementNode(ExpressionNode expression) {
    this.expression = expression;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    expression.semanticCheck(symbolTable, errorList);

    Type exprType = expression.getType(symbolTable);
    if (!(exprType instanceof Array) && !(exprType instanceof Pair)) {
      errorList.addError(new SemanticError(
              "'free' call expected type: Array or Pair, but given type: " + exprType.toString()));
    }
  }

  @Override
  public void generateAssembly(AssemblyGenerator generator,
                               SymbolTable symbolTable,
                               Stack<Register.ID> available) {
    expression.generateAssembly(generator, symbolTable, available);

    generateFreePair(generator, available);
  }

  private static void generateFreePair(AssemblyGenerator generator, Stack<Register.ID> available) {
    Register R0 = generator.getRegister(ID.R0);
    Register nextFree = generator.getRegister(available.peek());

    generator.addInstruction(new MovInstruction(R0, nextFree));

    generator.generateLabel(FREE_PAIR, new String[]
            {NULL_FREE}, AssemblyGenerator::free_pair);

    generator.addInstruction(new BranchInstruction(Condition.L, FREE_PAIR));
  }

  @Override
  public ShellStatementControl applyStatement(SymbolTable symbolTable,
      Heap heap) {
    // TODO
    return ShellStatementControl.cont();
  }
}

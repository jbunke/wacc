package frontend.abstractSyntaxTree.statements;


import backend.AssemblyGeneratorVisitor;
import backend.Register;
import backend.instructions.Instruction;
import frontend.abstractSyntaxTree.expressions.ExpressionNode;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class PrintStatementNode extends StatementNode {
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
  public List<Instruction> generateAssembly(AssemblyGeneratorVisitor generator,
                                            SymbolTable symbolTable,
                                            Stack<Register.ID> available) {
    return null;
  }
}

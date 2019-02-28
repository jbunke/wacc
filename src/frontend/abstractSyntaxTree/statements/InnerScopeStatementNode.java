package frontend.abstractSyntaxTree.statements;

import backend.AssemblyGenerator;
import backend.Register;
import backend.instructions.ArithInstruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;

import java.util.Stack;

public class InnerScopeStatementNode extends StatementNode {
  private final StatementNode innerStatement;

  public InnerScopeStatementNode(StatementNode innerStatement) {
    this.innerStatement = innerStatement;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    SymbolTable innerTable = symbolTable.newChild(innerStatement);
    innerStatement.semanticCheck(innerTable, errorList);
  }

  @Override
  public void generateAssembly(AssemblyGenerator generator,
                                            SymbolTable symbolTable,
                                            Stack<Register.ID> available) {
    int size = symbolTable.getChild(innerStatement).getSize();

    if (size > 0) {
      generator.addInstruction(
              ArithInstruction.sub(generator.getRegister(Register.ID.SP),
              generator.getRegister(Register.ID.SP), size));
    }
    innerStatement.generateAssembly(generator,
            symbolTable.getChild(innerStatement), available);
    if (size > 0) {
      generator.addInstruction(
              ArithInstruction.add(generator.getRegister(Register.ID.SP),
              generator.getRegister(Register.ID.SP), size));
    }
  }

  @Override
  public boolean endsWithReturn() {
    return innerStatement.endsWithReturn();
  }

  @Override
  public boolean containsExit() {
    return innerStatement.containsExit();
  }

  @Override
  public boolean containsReturn() {
    return innerStatement.containsReturn();
  }

  @Override
  public void matchReturnType(Type type) {
    innerStatement.matchReturnType(type);
  }
}

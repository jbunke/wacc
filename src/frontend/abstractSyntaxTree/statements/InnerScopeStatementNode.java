package frontend.abstractSyntaxTree.statements;

import backend.AssemblyGenerator;
import backend.Register;
import backend.instructions.Instruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;

import java.util.ArrayList;
import java.util.List;
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
  public List<Instruction> generateAssembly(AssemblyGenerator generator,
                                            SymbolTable symbolTable,
                                            Stack<Register.ID> available) {
    return innerStatement.generateAssembly(generator,
            symbolTable.getChild(innerStatement), available);
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

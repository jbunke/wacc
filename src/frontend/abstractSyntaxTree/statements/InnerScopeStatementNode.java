package frontend.abstractSyntaxTree.statements;

import backend.AssemblyGenerator;
import backend.Register;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;
import shell.ShellStatementControl;

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
    SymbolTable innerTable = symbolTable.getChild(innerStatement);

    generator.allocate(innerTable);

    innerStatement.generateAssembly(generator, innerTable, available);

    generator.deallocate(innerTable);
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

  @Override
  public ShellStatementControl applyStatement(SymbolTable symbolTable) {
    // TODO: check validity
    return innerStatement.applyStatement(symbolTable.getChild(innerStatement));
  }

  @Override
  public String toString() {
    return "begin\n" + innerStatement.toString() + "\nend";
  }
}

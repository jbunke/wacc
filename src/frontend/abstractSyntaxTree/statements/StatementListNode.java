package frontend.abstractSyntaxTree.statements;

import backend.AssemblyGenerator;
import backend.Register;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StatementListNode extends StatementNode {

  private final List<StatementNode> statements;

  public StatementListNode(List<StatementNode> statements) {
    this.statements = statements;
  }

  public List<StatementNode> getStatements() {
    return statements;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    for (StatementNode s : statements) {
      s.semanticCheck(symbolTable, errorList);
    }

    // checks all the identifiers in the scope to catch re-declaration
    List<String> identifierList = new ArrayList<>();
    for (StatementNode stat : statements) {
      if (stat instanceof DeclarationStatementNode) {
        identifierList.add(((DeclarationStatementNode) stat).getIdentifier().getName());
      }
    }
    for (int i = 0; i < identifierList.size(); i++) {
      for (int j = i + 1; j < identifierList.size(); j++) {
        if (identifierList.get(i).equals(identifierList.get(j))) {
          errorList.addError(new SemanticError("Variable already previously declared \""
                  + identifierList.get(i)
                  + "\" in this scope."));
        }
      }
    }
  }

  @Override
  public void generateAssembly(AssemblyGenerator generator,
                                            SymbolTable symbolTable,
                                            Stack<Register.ID> available) {
    for (StatementNode statement : statements) {
      statement.generateAssembly(generator, symbolTable, available);
    }
  }


  @Override
  public boolean endsWithReturn() {
    return statements.size() > 0 && (statements.get(statements.size() - 1).endsWithReturn());
  }

  // statement list contains return if any of its sub-statements do
  @Override
  public boolean containsReturn() {
    for (StatementNode stat : statements) {
      if (stat.containsReturn()) {
        return true;
      }
    }
    return false;
  }

  // matches the return type of the statements contained within
  @Override
  public void matchReturnType(Type type) {
    for (StatementNode stat : statements) {
      stat.matchReturnType(type);
    }
  }

  // statement list contains exit if any of its sub-statements do
  @Override
  public boolean containsExit() {
    for (StatementNode stat : statements) {
      if (stat.containsExit()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < statements.size(); i++) {
      sb.append(statements.get(i).toString());
      if (i < statements.size() - 1) {
        sb.append(" ;\n");
      }
    }
    return sb.toString();
  }
}

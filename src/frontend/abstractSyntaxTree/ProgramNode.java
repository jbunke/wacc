package frontend.abstractSyntaxTree;

import frontend.abstractSyntaxTree.statements.StatementNode;
import frontend.abstractSyntaxTree.typeNodes.FunctionDefinitionNode;
import frontend.symbolTable.Function;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;

import java.util.ArrayList;
import java.util.List;

public class ProgramNode implements Node {
  private final StatementNode stat;
  private final List<FunctionDefinitionNode> functions;

  public ProgramNode(List<FunctionDefinitionNode> functions, StatementNode stat) {
    this.functions = functions;
    this.stat = stat;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    for (FunctionDefinitionNode func : functions) {
      if (symbolTable.find(func.getIdentifier()) != null) {
        errorList.addError(new SemanticError("Attempted to redeclare" +
        " an existing function: \"" + func.getIdentifier() + ".\""));
      }
      symbolTable.add(func.getIdentifier(),
              new Function(func.getReturnType(), func.getParameterList()));
    }

    // Semantic checks for functions
    for (FunctionDefinitionNode func : functions) {
      SymbolTable funcTable = new SymbolTable(symbolTable);
      func.semanticCheck(funcTable, errorList);
    }

    // Semantic checks for program global statements
    SymbolTable statTable = new SymbolTable(symbolTable);
    stat.semanticCheck(statTable, errorList);
  }

  public List<String> syntaxCheck() {
    List<String> errors = new ArrayList<>();

    for (FunctionDefinitionNode func : functions) {
      String error = func.syntaxCheck();

      if (error != null) errors.add(error);
    }

    return errors;
  }
}

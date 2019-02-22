package frontend.abstractSyntaxTree;

import backend.AssemblyGeneratorVisitor;
import backend.Register;
import backend.instructions.*;
import frontend.abstractSyntaxTree.statements.StatementNode;
import frontend.abstractSyntaxTree.typeNodes.FunctionDefinitionNode;
import frontend.symbolTable.Function;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProgramNode implements Node {
  private final StatementNode stat;
  private final List<FunctionDefinitionNode> functions;

  public ProgramNode(List<FunctionDefinitionNode> functions, StatementNode stat) {
    this.functions = functions;
    this.stat = stat;
  }

  @Override
  public List<Instruction> generateAssembly(AssemblyGeneratorVisitor assemblyGeneratorVisitor, SymbolTable symbolTable) {
    List<Instruction> instructions = new ArrayList<>();
    instructions.add(new LabelInstruction("main"));
    instructions.add(new PushInstruction(registers.get(Register.ID.LR)));

    instructions.add(new PopInstruction(registers.get(Register.ID.PC)));
    instructions.add(new LTORGDirectiveInstruction());

    return instructions;
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
      SymbolTable funcTable = symbolTable.newChild();
      func.semanticCheck(funcTable, errorList);
    }

    // Semantic checks for program global statements
    SymbolTable statTable = symbolTable.newChild();
    stat.semanticCheck(statTable, errorList);

    if (stat.containsReturn()) {
      errorList.addError(new SemanticError(
              "Global return outside of function body attempted."));
    }
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

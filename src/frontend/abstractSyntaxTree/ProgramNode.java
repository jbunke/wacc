package frontend.abstractSyntaxTree;

import backend.AssemblyGenerator;
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
import java.util.Stack;

public class ProgramNode implements Node {
  private final StatementNode stat;
  private final List<FunctionDefinitionNode> functions;

  public ProgramNode(List<FunctionDefinitionNode> functions, StatementNode stat) {
    this.functions = functions;
    this.stat = stat;
  }

  @Override
  public List<Instruction> generateAssembly(AssemblyGenerator generator,
                                            SymbolTable symbolTable,
                                            Stack<Register.ID> available) {
    List<Instruction> instructions = new ArrayList<>();
    instructions.add(new PushInstruction(generator.getRegister(Register.ID.LR)));
    // Scope variable check
    int size = symbolTable.getChild(stat).getSize();


    if (size > 0) {
      instructions.add(ArithInstruction.sub(generator.getRegister(Register.ID.SP),
              generator.getRegister(Register.ID.SP), size));
    }

    instructions.addAll(stat.generateAssembly(generator,
            symbolTable.getChild(stat), available));
    // LDR r0, =0 is for successful program termination
    // TODO: only add instruction in case of successful termination

    if (size > 0) {
      instructions.add(ArithInstruction.add(generator.getRegister(Register.ID.SP),
              generator.getRegister(Register.ID.SP), size));
    }

    instructions.add(new LDRInstruction(generator
            .getRegister(Register.ID.R0), 0));
    instructions.add(new PopInstruction(generator.getRegister(Register.ID.PC)));
    instructions.add(new Directive(Directive.ID.LTORG));

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
      SymbolTable funcTable = symbolTable.newChild(func);
      func.semanticCheck(funcTable, errorList);
    }

    // Semantic checks for program global statements
    SymbolTable statTable = symbolTable.newChild(stat);
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

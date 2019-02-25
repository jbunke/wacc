package frontend.abstractSyntaxTree.expressions;

import backend.AssemblyGeneratorVisitor;
import backend.Register;
import backend.instructions.Instruction;
import backend.instructions.LDRInstruction;
import backend.instructions.MovInstruction;
import frontend.symbolTable.*;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IdentifierNode extends ExpressionNode {
  private final String identifier;

  public IdentifierNode(String identifier) {
    this.identifier = identifier;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {

    SymbolCategory id = symbolTable.find(identifier);
    if (id == null) {
      errorList.addError(new SemanticError(
              "Identifier \"" + identifier + "\" is used before it is declared."
      ));
    } else if (!(id instanceof Variable)) {
      errorList.addError(new SemanticError(
              "Identifier \"" + this.identifier + "\" used as a variable incorrectly"
      ));
    }
  }

  @Override
  public List<Instruction> generateAssembly(AssemblyGeneratorVisitor generator,
                                            SymbolTable symbolTable,
                                            Stack<Register.ID> available) {
    List<Instruction> instructions = new ArrayList<>();

    Register first = generator.getRegister(available.peek());
    instructions.add(new LDRInstruction(first,
            generator.getRegister(Register.ID.SP)));

    return instructions;
  }

  public String getName() {
    return identifier;
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    SymbolCategory symbolCategory = symbolTable.find(this.identifier);

    if (symbolCategory instanceof Variable) {
      return ((Variable) symbolCategory).getType();
    } else if (symbolCategory instanceof Function) {
      return ((Function) symbolCategory).getReturnType();
    }
    return new BaseTypes(BaseTypes.base_types.BOOL);
  }
}

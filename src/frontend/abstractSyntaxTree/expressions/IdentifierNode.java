package frontend.abstractSyntaxTree.expressions;

import backend.AssemblyGenerator;
import backend.Register;
import backend.instructions.LDRInstruction;
import frontend.symbolTable.*;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

import java.util.Stack;

public class IdentifierNode extends ExpressionNode {
  private final String identifier;

  public IdentifierNode(String identifier) {
    this.identifier = identifier;
  }

  @Override
  public int weight() {
    return 1;
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
  public void generateAssembly(AssemblyGenerator generator,
                               SymbolTable symbolTable,
                               Stack<Register.ID> available) {
    Register first = generator.getRegister(available.peek());
    boolean isSingleByte = getType(symbolTable).size() == 1;
    generator.addInstruction(new LDRInstruction(first,
            generator.getRegister(Register.ID.SP),
            symbolTable.fetchOffset(identifier))
            .isSingleByte(isSingleByte));
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

  @Override
  public Object evaluate(SymbolTable symbolTable) {
    return symbolTable.getValue(identifier);
  }

  @Override
  public String toString() {
    return identifier;
  }
}

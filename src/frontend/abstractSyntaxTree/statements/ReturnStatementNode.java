package frontend.abstractSyntaxTree.statements;

import backend.AssemblyGenerator;
import backend.Register;
import backend.instructions.MovInstruction;
import frontend.abstractSyntaxTree.expressions.ExpressionNode;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;

import java.util.Stack;

public class ReturnStatementNode extends StatementNode {
  private final ExpressionNode result;
  private Type returnType = null;

  public ReturnStatementNode(ExpressionNode result) {
    this.result = result;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    result.semanticCheck(symbolTable, errorList);

    if (returnType != null) {
      Type resultType = result.getType(symbolTable);
      if (resultType == null || !returnType.equals(resultType)) {
        errorList.addError(new SemanticError(
                "Return type does not match that specified in function declaration."));
      }
    }
  }

  @Override
  public void generateAssembly(AssemblyGenerator generator,
                                            SymbolTable symbolTable,
                                            Stack<Register.ID> available) {
    result.generateAssembly(generator, symbolTable, available);

    /* Move result (housed in first available general purpose register)
     * into R0 (return register) */
    generator.addInstruction(new MovInstruction(
            generator.getRegister(Register.ID.R0),
            generator.getRegister(available.peek())));
  }

  @Override
  public boolean containsReturn() {
    return true;
  }

  //all calls to this function in Statement Nodes should reach this call
  @Override
  public void matchReturnType(Type type) {
    returnType = type;
  }

  public Type getType(SymbolTable symbolTable) {
    return result.getType(symbolTable);
  }

  @Override
  public boolean endsWithReturn() {
    return true;
  }
}

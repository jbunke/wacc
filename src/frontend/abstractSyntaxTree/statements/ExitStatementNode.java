package frontend.abstractSyntaxTree.statements;


import backend.Register;
import backend.instructions.Instruction;
import frontend.abstractSyntaxTree.expressions.ExpressionNode;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

import java.util.List;
import java.util.Map;

public class ExitStatementNode extends StatementNode {
  private final ExpressionNode exitCode;

  public ExitStatementNode(ExpressionNode exitCode) {
    this.exitCode = exitCode;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {

    exitCode.semanticCheck(symbolTable, errorList);

    Type resultType = exitCode.getType(symbolTable);
    if (resultType == null || !resultType.equals(new BaseTypes(BaseTypes.base_types.INT))) {
      errorList.addError(new SemanticError("Can't exit program with non-integer value."));
    }
  }

  @Override
  public List<Instruction> generateAssembly(Map<Register.ID, Register> registers, SymbolTable symbolTable) {
    return null;
  }

  @Override
  public boolean endsWithReturn() {
    return true;
  }

  @Override
  public boolean containsExit() {
    return true;
  }
}

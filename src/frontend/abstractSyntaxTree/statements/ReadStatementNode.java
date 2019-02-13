package frontend.abstractSyntaxTree.statements;

import frontend.abstractSyntaxTree.assignment.AssignLHS;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.BaseTypes;

public class ReadStatementNode extends StatementNode {
  private final AssignLHS lhs;

  public ReadStatementNode(AssignLHS lhs) {
    this.lhs = lhs;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    if (!lhs.getType(symbolTable).equals(new BaseTypes(BaseTypes.base_types.CHAR)) && !lhs.getType(symbolTable)
            .equals(new BaseTypes(
                    BaseTypes
                            .base_types.INT))) {
      errorList.addError(new SemanticError("Standard input only allows character or integer "
              + "Variable given: \""
              + lhs.getType(symbolTable).toString()
              + "\"."));
    }
    lhs.semanticCheck(symbolTable, errorList);
  }
}

package frontend.abstractSyntaxTree.statements;

import backend.Register;
import backend.instructions.Instruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;

import java.util.List;

public class SkipStatementNode extends StatementNode {

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
  }

  @Override
  public List<Instruction> generateAssembly(Map<Register.ID, Register> registers, SymbolTable symbolTable) {
    return null;
  }
}

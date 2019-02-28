package frontend.abstractSyntaxTree.statements;

import backend.AssemblyGenerator;
import backend.Register;
import backend.instructions.Instruction;
import backend.instructions.ArithInstruction;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InnerScopeStatementNode extends StatementNode {
  private final StatementNode innerStatement;

  public InnerScopeStatementNode(StatementNode innerStatement) {
    this.innerStatement = innerStatement;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    SymbolTable innerTable = symbolTable.newChild(innerStatement);
    innerStatement.semanticCheck(innerTable, errorList);
  }

  @Override
  public List<Instruction> generateAssembly(AssemblyGenerator generator,
                                            SymbolTable symbolTable,
                                            Stack<Register.ID> available) {
    List<Instruction> instructions = new ArrayList<>();

    int size = symbolTable.getChild(innerStatement).getSize();

    if (size > 0) {
      instructions.add(ArithInstruction.sub(generator.getRegister(Register.ID.SP),
              generator.getRegister(Register.ID.SP), size));
    }
    instructions.addAll(innerStatement.generateAssembly(generator,
            symbolTable.getChild(innerStatement), available));
    if (size > 0) {
      instructions.add(ArithInstruction.add(generator.getRegister(Register.ID.SP),
              generator.getRegister(Register.ID.SP), size));
    }

    return instructions;
  }

  @Override
  public boolean endsWithReturn() {
    return innerStatement.endsWithReturn();
  }

  @Override
  public boolean containsExit() {
    return innerStatement.containsExit();
  }

  @Override
  public boolean containsReturn() {
    return innerStatement.containsReturn();
  }

  @Override
  public void matchReturnType(Type type) {
    innerStatement.matchReturnType(type);
  }
}

package frontend.abstractSyntaxTree.statements;

import backend.AssemblyGeneratorVisitor;
import backend.Register;
import backend.instructions.Instruction;
import backend.instructions.STRInstruction;
import frontend.abstractSyntaxTree.assignment.AssignLHS;
import frontend.abstractSyntaxTree.assignment.AssignRHS;
import frontend.abstractSyntaxTree.typeNodes.BaseTypesNode;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class AssignVariableStatementNode extends StatementNode {

  private final AssignLHS left;
  private final AssignRHS right;

  public AssignVariableStatementNode(AssignLHS left, AssignRHS right) {
    this.left = left;
    this.right = right;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    left.semanticCheck(symbolTable, errorList);
    right.semanticCheck(symbolTable, errorList);

    final Type leftType = left.getType(symbolTable);
    final Type rightType = this.right.getType(symbolTable);
    if (leftType == null) {
      errorList.addError(new SemanticError("LHS type cannot be resolved"));
    } else if (rightType == null) {
      errorList.addError(new SemanticError("RHS type cannot be resolved"));
    } else if (!leftType.equals(rightType)) {
      errorList.addError(new SemanticError("Type on LHS \""
              + leftType.toString()
              + "\" does not match \""
              + rightType.toString()
              + "\" on RHS."));
    }
  }

  @Override
  public List<Instruction> generateAssembly(AssemblyGeneratorVisitor generator,
                                            SymbolTable symbolTable,
                                            Stack<Register.ID> available) {
    List<Instruction> instructions = new ArrayList<>();

    instructions.addAll(right.generateAssembly(generator,
            symbolTable, available));
    instructions.add(new STRInstruction(
            generator.getRegister(available.peek()),
            generator.getRegister(Register.ID.SP)));

    return instructions;
  }
}

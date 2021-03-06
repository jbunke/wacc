package frontend.abstractSyntaxTree.statements;

import static shell.WACCShell.ANSI_RED;
import static shell.WACCShell.ANSI_RESET;

import backend.AssemblyGenerator;
import backend.Register;
import backend.instructions.STRInstruction;
import frontend.abstractSyntaxTree.assignment.AssignLHS;
import frontend.abstractSyntaxTree.assignment.AssignPairElementNode;
import frontend.abstractSyntaxTree.assignment.AssignRHS;
import frontend.abstractSyntaxTree.expressions.ArrayElementNode;
import frontend.abstractSyntaxTree.expressions.IdentifierNode;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;
import shell.structural.Heap;
import shell.structural.PairVariableValue;
import shell.structural.ShellStatementControl;

import java.util.Stack;

public class AssignVariableStatementNode extends StatementNode {
  private static final String RUNTIME_ERROR = "Runtime Error:";

  private final AssignLHS left;
  private final AssignRHS right;

  public AssignVariableStatementNode(AssignLHS left, AssignRHS right) {
    this.left = left;
    this.right = right;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable,
                            SemanticErrorList errorList) {
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
  public void generateAssembly(AssemblyGenerator generator,
                               SymbolTable symbolTable,
                               Stack<Register.ID> available) {
    boolean isSingleByte = left.getType(symbolTable).isSingleByte();

    right.generateAssembly(generator, symbolTable, available);

    if (left instanceof IdentifierNode) {
      IdentifierNode ident = (IdentifierNode) left;
      generator.addInstruction(new STRInstruction(
              generator.getRegister(available.peek()),
              generator.getRegister(Register.ID.SP),
              symbolTable.fetchOffset(ident.getName()), isSingleByte));
    } else if (left instanceof AssignPairElementNode) {
      AssignPairElementNode pair = (AssignPairElementNode) left;
      pair.generateLHSAssembly(generator, symbolTable, available);
    } else if (left instanceof ArrayElementNode) {
      ArrayElementNode a = (ArrayElementNode) left;
      a.generateLHSAssembly(generator, symbolTable, available, isSingleByte);
    } else {
      generator.addInstruction(new STRInstruction(
              generator.getRegister(available.peek()),
              generator.getRegister(Register.ID.SP), isSingleByte));
    }
  }

  @Override
  public String toString() {
    return left.toString() + " = " + right.toString();
  }

  @Override
  public ShellStatementControl applyStatement(SymbolTable symbolTable,
      Heap heap) {
    String identifier = "";

    // Get value
    Object value = right.evaluate(symbolTable, heap);

    if (isValueErroneous(value)) {

      System.out.print(ANSI_RED);
      System.out.println(value);
      System.out.print(ANSI_RESET);

    } else {

      // Get identifier
      if (left instanceof IdentifierNode) {

        identifier = ((IdentifierNode) left).getName();
        symbolTable.setValue(identifier, value);

      } else if (left instanceof AssignPairElementNode) {

        AssignPairElementNode pairNode = (AssignPairElementNode) left;
        PairVariableValue v = (PairVariableValue)
            symbolTable.getValue(pairNode.getIdentifier());

        if (pairNode.getPosition() == AssignPairElementNode.FST_POSITION) {
          v.setLeft(value);
        } else if (pairNode.getPosition()
            == AssignPairElementNode.SND_POSITION) {
          v.setRight(value);
        }

      } else if (left instanceof ArrayElementNode) {
        ArrayElementNode arrayNode = (ArrayElementNode) left;

        Object result = arrayNode.updateElement(symbolTable, heap, value);

        if (isValueErroneous(result)) {
          System.out.print(ANSI_RED);
          System.out.println(result);
          System.out.print(ANSI_RESET);
        }
      }
    }

    return ShellStatementControl.cont();
  }

  private static boolean isValueErroneous(Object value) {
    return (value instanceof String)
        && ((String) value).startsWith(RUNTIME_ERROR);
  }
}

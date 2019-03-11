package frontend.abstractSyntaxTree.assignment;

import backend.AssemblyGenerator;
import backend.Condition;
import backend.Register;
import backend.Register.ID;
import backend.instructions.BranchInstruction;
import backend.instructions.LDRInstruction;
import backend.instructions.MovInstruction;
import backend.instructions.STRInstruction;
import frontend.abstractSyntaxTree.expressions.ExpressionNode;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Pair;
import frontend.symbolTable.types.Type;

import java.util.Stack;

public class NewPairNode implements AssignRHS {

  private static final int NUM_PAIR_ELEMENTS = 2;

  private static final int LEFT_ELEM = 1;
  private static final int RIGHT_ELEM = 2;

  private final ExpressionNode leftExpression;
  private final ExpressionNode rightExpression;

  public NewPairNode(ExpressionNode leftExpression,
                     ExpressionNode rightExpression) {
    this.leftExpression = leftExpression;
    this.rightExpression = rightExpression;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList eList) {
  }

  @Override
  public void generateAssembly(AssemblyGenerator generator,
                               SymbolTable symbolTable,
                               Stack<Register.ID> available) {
    generator.addInstruction(new LDRInstruction(generator.getRegister(ID.R0),
            NUM_PAIR_ELEMENTS * ADDR_SIZE));
    generator.addInstruction(new BranchInstruction(Condition.L, "malloc"));

    Register ptrReg = generator.getRegister(available.pop());
    generator.addInstruction(new MovInstruction(ptrReg,
            generator.getRegister(ID.R0)));

    allocElem(generator, symbolTable, available, LEFT_ELEM, ptrReg);
    allocElem(generator, symbolTable, available, RIGHT_ELEM, ptrReg);

    available.push(ptrReg.getRegID());
  }

  private void allocElem(AssemblyGenerator generator, SymbolTable symbolTable,
                         Stack<Register.ID> available, int elem, Register ptrReg) {
    ExpressionNode exp;
    if (elem == LEFT_ELEM) {
      exp = leftExpression;
    } else {
      exp = rightExpression;
    }
    exp.generateAssembly(generator, symbolTable, available);

    Register r0 = generator.getRegister(ID.R0);

    int typeSize = exp.getType(symbolTable).size();
    generator.addInstruction(new LDRInstruction(r0, typeSize));
    generator.addInstruction(new BranchInstruction(Condition.L, "malloc"));

    boolean isSingleByte = typeSize == BYTE_SIZE;
    generator.addInstruction(
            new STRInstruction(generator.getRegister(available.peek()),
                    r0, isSingleByte));

    generator.addInstruction(new STRInstruction(r0, ptrReg,
            (elem - 1) * ADDR_SIZE, false));
  }


  @Override
  public Type getType(SymbolTable symbolTable) {
    return new Pair(leftExpression.getType(symbolTable),
            rightExpression.getType(symbolTable));
  }
}

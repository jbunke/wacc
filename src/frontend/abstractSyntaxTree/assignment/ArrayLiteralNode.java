package frontend.abstractSyntaxTree.assignment;


import backend.AssemblyGenerator;
import backend.Register;
import backend.Condition;
import backend.Register.ID;
import backend.instructions.BranchInstruction;
import backend.instructions.LDRInstruction;
import backend.instructions.MovInstruction;
import backend.instructions.STRInstruction;
import frontend.abstractSyntaxTree.expressions.ExpressionNode;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Array;
import frontend.symbolTable.types.Type;
import frontend.symbolTable.types.BaseTypes;

import java.util.List;
import java.util.Stack;

public class ArrayLiteralNode implements AssignRHS {

  private static final int BYTE_SIZE = 1;

  private final List<ExpressionNode> arrayElements;

  public ArrayLiteralNode(List<ExpressionNode> arrayElements) {
    this.arrayElements = arrayElements;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable,
      SemanticErrorList errorList) {
    if (arrayElements.size() > 1) {
      final Type type = arrayElements.get(0).getType(symbolTable);

      for (ExpressionNode expr : arrayElements) {
        if (!expr.getType(symbolTable).equals(type)) {
          errorList.addError(new SemanticError(
              "Array with elements of multiple types not supported by WACC"
          ));
        }
      }
    }
    for (ExpressionNode expr : arrayElements) {
      expr.semanticCheck(symbolTable, errorList);
    }
  }

  @Override
  public void generateAssembly(AssemblyGenerator generator,
      SymbolTable symbolTable,
      Stack<Register.ID> available) {
    Type elemType = ((Array) this.getType(symbolTable)).getElementType();
    int elemSize = elemType == null ? 0 : elemType.size();
    boolean isSingleByte = elemSize == BYTE_SIZE;

    int arraySize = elemSize * arrayElements.size() + BaseTypes.INT_SIZE;
    generator
        .addInstruction(new LDRInstruction(generator.getRegister(ID.R0), arraySize));
    generator.addInstruction(new BranchInstruction(Condition.L, "malloc"));

    Register allocatorReg = generator.getRegister(available.peek());
    available.pop();
    generator
        .addInstruction(new MovInstruction(allocatorReg, generator.getRegister(ID.R0)));

    int i = BaseTypes.INT_SIZE;
    for (ExpressionNode e : arrayElements) {
      e.generateAssembly(generator, symbolTable, available);
      Register resultReg = generator.getRegister(available.peek());
      generator.addInstruction(
          new STRInstruction(resultReg, allocatorReg, i,
              isSingleByte));
      i += elemSize;
    }

    Register sizeParameterRegister = generator.getRegister(available.peek());
    generator.addInstruction(new LDRInstruction(sizeParameterRegister,
        arrayElements.size()));
    generator
        .addInstruction(new STRInstruction(sizeParameterRegister, allocatorReg, false));

    available.push(allocatorReg.getRegID());
  }

  @Override
  public Type getType(SymbolTable symbolTable) {

    if (arrayElements.size() == 0) {
      return new Array(null);
    } else {
      return new Array(arrayElements.get(0).getType(symbolTable));
    }
  }
}

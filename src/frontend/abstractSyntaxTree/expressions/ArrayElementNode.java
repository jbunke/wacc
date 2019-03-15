package frontend.abstractSyntaxTree.expressions;


import backend.AssemblyGenerator;
import backend.Condition;
import backend.Register;
import backend.Register.ID;
import backend.instructions.*;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.Variable;
import frontend.symbolTable.types.Array;
import frontend.symbolTable.types.BaseTypes;
import frontend.symbolTable.types.Type;

import java.util.List;
import java.util.Stack;
import shell.ArrayVariableValue;
import shell.Heap;

public class ArrayElementNode extends ExpressionNode {

  private static final int INT_SIZE = 4;
  private static final int LEFT_SHIFT_ARR = 2;

  private static final String CHECK_ARRAY_BOUNDS = "p_check_array_bounds";
  private static final String CHECK_ARRAY_LOWER =
          "ArrayIndexOutOfBoundsError: " +
                  "negative index\\n\\0";
  private static final String CHECK_ARRAY_UPPER =
          "ArrayIndexOutOfBoundsError: " +
                  "index too large\\n\\0";
  private final IdentifierNode identifier;
  private final List<ExpressionNode> indices;

  public ArrayElementNode(List<ExpressionNode> indices,
                          IdentifierNode identifier) {
    this.indices = indices;
    this.identifier = identifier;
  }

  @Override
  public int weight() {
    int weight = 2;

    for (ExpressionNode index : indices) {
      weight += index.weight();
    }

    return weight;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable,
                            SemanticErrorList errorList) {
    for (ExpressionNode node : indices) {
      final Type nodeType = node.getType(symbolTable);
      if (!nodeType.equals(new BaseTypes(BaseTypes.base_types.INT))) {
        errorList.addError(new SemanticError(
                "Error: Index used for array is not an integer!" + "" +
                        "Expression supplied was of type \"" + nodeType.toString()
                        + "\"."
        ));
      }
    }

    final Type identifierType = identifier.getType(symbolTable);
    if (!(identifierType instanceof Array)) {
      errorList.addError(new SemanticError(
              "Error: Tried to index a variable of non-array type. " +
                      "Variable is of type \"" + identifierType.toString() + "\"."
      ));
    }

    int dimensions = 0;
    Type t = identifierType;
    while (t instanceof Array) {
      t = ((Array) t).getElementType();
      dimensions++;
    }

    if (indices.size() > dimensions) {
      errorList.addError(new SemanticError(
              "Error: Too many indices! Array only has" + dimensions
                      + " dimensions, " +
                      "but has been supplied" + indices.size() + " indices."
      ));
    }
  }

  @Override
  public void generateAssembly(AssemblyGenerator generator,
                               SymbolTable symbolTable, Stack<Register.ID> available) {
    Register addrReg = generator.getRegister(available.pop());

    getAddrOfElem(generator, symbolTable, available, addrReg);

    generator.addInstruction(new LDRInstruction(addrReg, addrReg));

    available.push(addrReg.getRegID());
  }

  private void getAddrOfElem(AssemblyGenerator generator, SymbolTable symbolTable,
                             Stack<Register.ID> available, Register addrReg) {
    Register R0 = generator.getRegister(ID.R0);
    Register R1 = generator.getRegister(ID.R1);
    Register nextFree = generator.getRegister(available.peek());
    Register SP = generator.getRegister(ID.SP);

    generator.addInstruction(ArithInstruction
            .add(addrReg, SP, symbolTable.fetchOffset(identifier.getName())));

    for (ExpressionNode e : indices) {
      e.generateAssembly(generator, symbolTable, available);

      generator.addInstruction(new LDRInstruction(addrReg, addrReg));
      generator.addInstruction(new MovInstruction(R0, nextFree));
      generator.addInstruction(new MovInstruction(R1, addrReg));

      generator.generateLabel(CHECK_ARRAY_BOUNDS, new String[]
                      {CHECK_ARRAY_LOWER, CHECK_ARRAY_UPPER},
              AssemblyGenerator::check_array_bounds);
      generator.addInstruction(
              new BranchInstruction(Condition.L, CHECK_ARRAY_BOUNDS));
      generator
              .addInstruction(ArithInstruction.add(addrReg, addrReg, INT_SIZE));
      generator.addInstruction(
              ArithInstruction.addReg(addrReg, addrReg, nextFree, LEFT_SHIFT_ARR));
    }
  }

  public void generateLHSAssembly(AssemblyGenerator generator,
                                  SymbolTable symbolTable,
                                  Stack<Register.ID> available, boolean isSingleByte) {

    Register RHSResult = generator.getRegister(available.pop());
    Register addrReg = generator.getRegister(available.pop());

    getAddrOfElem(generator, symbolTable, available, addrReg);

    generator.addInstruction(new STRInstruction(RHSResult, addrReg, isSingleByte));

    available.push(addrReg.getRegID());
    available.push(RHSResult.getRegID());
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    Variable array = (Variable) symbolTable.find(identifier.getName());
    Array current = (Array) array.getType();

    for (int i = indices.size(); i > 1; i--) {
      current = (Array) current.getElementType();
    }

    return current.getElementType();
  }

  @Override
  public Object evaluate(SymbolTable symbolTable, Heap heap) {
    ArrayVariableValue v = (ArrayVariableValue)
        symbolTable.getValue(identifier.getName());
    Object res = null;

    for (ExpressionNode e : indices) {

      int i = (int) e.evaluate(symbolTable, heap);

      if (!v.indexInUpperBound(i)) {
        return "Runtime Error: array index too large!";
      } else if (!v.indexInLowerBound(i)) {
        return "Runtime Error: array index less than " +
            ArrayVariableValue.MIN_ARRAY_INDEX + "!";
      } else {
        res = v.getElementAtIndex(i);

        if (res instanceof ArrayVariableValue) {
          v = (ArrayVariableValue) res;
        }
      }
    }

    return res;
  }

  public Object updateElement(SymbolTable symbolTable, Heap heap, Object value) {
    ArrayVariableValue v = (ArrayVariableValue)
        symbolTable.getValue(identifier.getName());
    Object res;

    for (int i = 0; i < indices.size(); i++) {
      ExpressionNode e = indices.get(i);
      int j = (int) e.evaluate(symbolTable, heap);

      if (!v.indexInUpperBound(j)) {
        return "Runtime Error: array index too large!";
      } else if (!v.indexInLowerBound(j)) {
        return "Runtime Error: array index less than " +
            ArrayVariableValue.MIN_ARRAY_INDEX + "!";
      } else {
        /* If we have pushed as deep into the array as necessary to update */
        if (i == indices.size() - 1) {
          v.updateElement(j, value);
        } else {
          res = v.getElementAtIndex(i);
          v = (ArrayVariableValue) res;
        }
      }
    }

    return value;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(identifier.getName());
    for (ExpressionNode index : indices) {
      sb.append("[");
      sb.append(index.toString());
      sb.append("]");
    }
    return sb.toString();
  }
}

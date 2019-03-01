package frontend.abstractSyntaxTree.assignment;

import backend.AssemblyGenerator;
import backend.Condition;
import backend.Register;
import backend.Register.ID;
import backend.instructions.BranchInstruction;
import backend.instructions.Instruction;
import backend.instructions.LDRInstruction;
import backend.instructions.MovInstruction;
import frontend.abstractSyntaxTree.expressions.IdentifierNode;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Pair;
import frontend.symbolTable.types.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AssignPairElementNode implements AssignRHS {

  private static final String NULL_PTR_CHECK = "p_check_null_pointer";
  private static final String NULL_PTR = "NullReferenceError: dereference "
      + "a null reference\\n\\0";

  private static final int FST_POSITION = 0;
  private static final int SND_POSITION = 1;

  private final IdentifierNode identifier;
  private final int position;

  public AssignPairElementNode(IdentifierNode id, int position) {
    this.identifier = id;
    this.position = position;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    Type type = identifier.getType(symbolTable);
    if (!(type instanceof Pair)) {
      errorList.addError(new SemanticError("Not a pair type"));
    }
  }

  @Override
  public void generateAssembly(AssemblyGenerator generator,
      SymbolTable symbolTable,
      Stack<Register.ID> available) {

    Register firstFreeReg = generator.getRegister(available.peek());
    Register R0 = generator.getRegister(ID.R0);

    identifier.generateAssembly(generator, symbolTable, available);

    generator.addInstruction(new MovInstruction(R0, firstFreeReg));
    generateNullPtrCheck(generator);
    generator.addInstruction(new BranchInstruction(Condition.L, NULL_PTR_CHECK));

    Pair p = ((Pair) identifier.getType(symbolTable));
    Type elem = position == FST_POSITION ? p.getFirst() : p.getSecond();

    boolean isSingleByte = elem.size() == BYTE_SIZE;

    generator.addInstruction(new LDRInstruction(firstFreeReg, firstFreeReg, position * ADDR_SIZE));
    generator.addInstruction(new LDRInstruction(firstFreeReg, firstFreeReg).isSingleByte(isSingleByte));
  }

  private static void generateNullPtrCheck(AssemblyGenerator generator) {
    generator.generateLabel(NULL_PTR_CHECK, new String[]
        {NULL_PTR}, AssemblyGenerator::check_null_ptr);
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    Pair pair = (Pair) identifier.getType(symbolTable);
    if (position == 0) {
      return pair.getFirst();
    } else {
      return pair.getSecond();
    }

  }

}

package frontend.abstractSyntaxTree.assignment;

import backend.AssemblyGenerator;
import backend.Condition;
import backend.Register;
import backend.Register.ID;
import backend.instructions.BranchInstruction;
import backend.instructions.LDRInstruction;
import backend.instructions.MovInstruction;
import backend.instructions.STRInstruction;
import frontend.abstractSyntaxTree.expressions.IdentifierNode;
import frontend.symbolTable.SemanticError;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Pair;
import frontend.symbolTable.types.Type;

import java.util.Stack;

public class AssignPairElementNode implements AssignRHS {

  private static final String NULL_PTR_CHECK = "p_check_null_pointer";
  private static final String NULL_PTR = "NullReferenceError: dereference "
          + "a null reference\\n\\0";

  public static final int FST_POSITION = 0;
  public static final int SND_POSITION = 1;

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

    Register nextFree = generator.getRegister(available.peek());
    Register R0 = generator.getRegister(ID.R0);

    identifier.generateAssembly(generator, symbolTable, available);

    generateNullPtrCheck(generator, available);

    generator.addInstruction(new LDRInstruction(nextFree, nextFree, position * ADDR_SIZE));
    generator.addInstruction(new LDRInstruction(nextFree, nextFree).isSingleByte(isSingleByte(symbolTable)));
  }

  private static void generateNullPtrCheck(AssemblyGenerator generator, Stack<Register.ID> available) {
    Register R0 = generator.getRegister(ID.R0);
    Register nextFree = generator.getRegister(available.peek());

    generator.addInstruction(new MovInstruction(R0, nextFree));

    generator.generateLabel(NULL_PTR_CHECK, new String[]
            {NULL_PTR}, AssemblyGenerator::check_null_ptr);

    generator.addInstruction(new BranchInstruction(Condition.L, NULL_PTR_CHECK));
  }

  public void generateLHSAssembly(AssemblyGenerator generator,
                                  SymbolTable symbolTable,
                                  Stack<Register.ID> available) {
    Register RHSResult = generator.getRegister(available.pop());
    Register nextFree = generator.getRegister(available.peek());
    Register SP = generator.getRegister(ID.SP);
    Register R0 = generator.getRegister(ID.R0);

    generator.addInstruction(new LDRInstruction(nextFree, SP,
            symbolTable.fetchOffset(identifier.getName())));

    generateNullPtrCheck(generator, available);

    generator.addInstruction(new LDRInstruction(nextFree, nextFree, position * ADDR_SIZE));
    generator.addInstruction(new STRInstruction(RHSResult, nextFree, isSingleByte(symbolTable)));

    available.push(RHSResult.getRegID());
  }

  private boolean isSingleByte(SymbolTable symbolTable) {
    Pair p = ((Pair) identifier.getType(symbolTable));
    Type elem = position == FST_POSITION ? p.getFirst() : p.getSecond();

    return elem.size() == BYTE_SIZE;
  }

  @Override
  public Type getType(SymbolTable symbolTable) {
    Pair pair = (Pair) identifier.getType(symbolTable);
    return position == FST_POSITION ? pair.getFirst() : pair.getSecond();
  }

}

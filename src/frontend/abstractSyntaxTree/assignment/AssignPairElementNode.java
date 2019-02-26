package frontend.abstractSyntaxTree.assignment;

import backend.AssemblyGenerator;
import backend.Register;
import backend.instructions.Instruction;
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
  public List<Instruction> generateAssembly(AssemblyGenerator generator,
                                            SymbolTable symbolTable,
                                            Stack<Register.ID> available) {
    return new ArrayList<>();
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

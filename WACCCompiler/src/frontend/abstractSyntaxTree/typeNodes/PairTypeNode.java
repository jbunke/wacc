package frontend.abstractSyntaxTree.typeNodes;

import backend.AssemblyGenerator;
import backend.Register;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Pair;
import frontend.symbolTable.types.Type;

import java.util.Stack;

public class PairTypeNode extends TypeNode {
  private final TypeNode firstType;
  private final TypeNode secondType;

  public PairTypeNode(TypeNode fstType, TypeNode sndType) {
    this.firstType = fstType;
    this.secondType = sndType;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    firstType.semanticCheck(symbolTable, errorList);
    secondType.semanticCheck(symbolTable, errorList);
  }

  @Override
  public void generateAssembly(AssemblyGenerator generator,
                               SymbolTable symbolTable,
                               Stack<Register.ID> available) {
  }

  @Override
  public Type getType() {
    return new Pair(firstType.getType(), secondType.getType());
  }

  @Override
  public String toString() {
    return "pair(" + firstType.toString().toLowerCase() +
            ", " + secondType.toString().toLowerCase() + ")";
  }
}

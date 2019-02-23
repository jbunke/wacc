package frontend.abstractSyntaxTree.typeNodes;

import backend.AssemblyGeneratorVisitor;
import backend.Register;
import backend.instructions.Instruction;
import frontend.abstractSyntaxTree.Node;
import frontend.abstractSyntaxTree.expressions.IdentifierNode;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.Variable;
import frontend.symbolTable.types.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ParameterListNode implements Node {
  private final List<IdentifierNode> identifierList;
  private final List<TypeNode> types;

  public ParameterListNode(List<IdentifierNode> identifierList, List<TypeNode> types) {
    this.identifierList = identifierList;
    this.types = types;
  }

  // to be used to look up identifiers in body to match to parameters
  private TypeNode fromParameters(IdentifierNode node) {
    for (int i = 0; i < this.identifierList.size(); i++) {
      if (this.identifierList.get(i).getName().equals(node.getName())) {
        return types.get(i);
      }
    }
    return null;
  }

  public List<Type> getParamTypes() {
    List<Type> paramTypes = new ArrayList<>();
    for (TypeNode node : types) {
      paramTypes.add(node.getType());
    }
    return paramTypes;
  }

  @Override
  public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
    for (IdentifierNode id : identifierList) {
      symbolTable.add(id.getName(), new Variable(fromParameters(id).getType()));
      fromParameters(id).semanticCheck(symbolTable, errorList);
    }
  }

  @Override
  public List<Instruction> generateAssembly(AssemblyGeneratorVisitor generator,
                                            SymbolTable symbolTable,
                                            Stack<Register.ID> available) {
    return new ArrayList<>();
  }


}

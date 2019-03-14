package frontend.abstractSyntaxTree.typeNodes;

import backend.AssemblyGenerator;
import backend.Register;
import backend.instructions.Directive;
import backend.instructions.LabelInstruction;
import backend.instructions.PopInstruction;
import backend.instructions.PushInstruction;
import frontend.abstractSyntaxTree.Node;
import frontend.abstractSyntaxTree.expressions.IdentifierNode;
import frontend.abstractSyntaxTree.statements.StatementNode;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolCategory;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;

import java.util.Stack;

public class FunctionDefinitionNode extends SymbolCategory implements Node {
  private final IdentifierNode identifier;
  private final StatementNode body;
  private final ParameterListNode parameters;
  private final TypeNode returnType;

  public FunctionDefinitionNode(TypeNode type, IdentifierNode identifier,
                                ParameterListNode parameters,
                                StatementNode stat) {
    this.returnType = type;
    this.identifier = identifier;
    this.parameters = parameters;
    this.body = stat;
  }

  public StatementNode getBody() {
    return body;
  }

  public Type getReturnType() {
    return returnType.getType();
  }

  public String getIdentifier() {
    return identifier.getName();
  }

  public ParameterListNode getParameterList() {
    return parameters;
  }


  @Override
  public void semanticCheck(SymbolTable symbolTable,
                            SemanticErrorList errorList) {
    /* Check to see if is using symbol table with correct scope
     * This will not be the case in the interactive shell where
     *   functions are not called from the program node */
    symbolTable = (symbolTable.matchesScope(this))
            ? symbolTable : symbolTable.newChild(this);

    body.matchReturnType(getReturnType());
    parameters.semanticCheck(symbolTable, errorList);
    body.semanticCheck(symbolTable, errorList);
  }

  @Override
  public void generateAssembly(
          AssemblyGenerator generator,
          SymbolTable symbolTable, Stack<Register.ID> available) {
    String funcLabel = "f_" + identifier.getName();
    generator.setActiveLabel(funcLabel);
    generator.addInstruction(new LabelInstruction(funcLabel));

    generator.addInstruction(new PushInstruction(generator
            .getRegister(Register.ID.LR)));

    parameters.generateAssembly(generator, symbolTable, available);
    generator.allocate(symbolTable);
    body.generateAssembly(generator, symbolTable, available);

    generator.addInstruction(new PopInstruction(generator
            .getRegister(Register.ID.PC)));
    generator.addInstruction(new Directive(Directive.ID.LTORG));
    generator.setActiveLabel("main");
  }

  public String syntaxCheck() {
    if (!body.containsReturn() && !body.containsExit()) {
      return "Function \"" + identifier.getName() +
              "\" has no return or exit statement.";
    } else if (!body.endsWithReturn()) {
      return "Function  \"" + identifier.getName() +
              "\" contains statements after return statement.";
    }
    return null;
  }

}
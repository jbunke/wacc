package frontend.abstractSyntaxTree.typeNodes;

import backend.AssemblyGenerator;
import backend.Register;
import backend.instructions.*;
import frontend.abstractSyntaxTree.Node;
import frontend.abstractSyntaxTree.expressions.IdentifierNode;
import frontend.abstractSyntaxTree.statements.StatementNode;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;

import java.util.Stack;

public class FunctionDefinitionNode implements Node {
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
        body.matchReturnType(getReturnType());
        parameters.semanticCheck(symbolTable, errorList);
        body.semanticCheck(symbolTable, errorList);
    }

    @Override
    public void generateAssembly(
            AssemblyGenerator generator,
            SymbolTable symbolTable, Stack<Register.ID> available) {

        // Push instruction
        generator.addInstruction(new PushInstruction(generator
                .getRegister(Register.ID.LR)));
        // Add all instructions from function body
        body.generateAssembly(generator, symbolTable, available);

        // Pop instruction
        generator.addInstruction(new PopInstruction(generator
                .getRegister(Register.ID.PC)));

        // Add LTORG directive
        generator.addInstruction(new Directive(Directive.ID.LTORG));
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
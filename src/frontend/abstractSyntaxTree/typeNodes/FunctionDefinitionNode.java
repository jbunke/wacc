package frontend.abstractSyntaxTree.typeNodes;

import backend.AssemblyGeneratorVisitor;
import backend.Register;
import backend.instructions.Instruction;
import backend.instructions.LabelInstruction;
import backend.instructions.PushInstruction;
import frontend.abstractSyntaxTree.Node;
import frontend.abstractSyntaxTree.expressions.IdentifierNode;
import frontend.abstractSyntaxTree.statements.StatementNode;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public List<Instruction> generateAssembly(
            AssemblyGeneratorVisitor assemblyGeneratorVisitor,
            SymbolTable symbolTable) {

        List<Instruction> instructions = new ArrayList<>();

        // Push instruction
        instructions.add(new PushInstruction(assemblyGeneratorVisitor
                .getRegister(Register.ID.LR)));

        // Add all instructions from function body
        instructions.addAll(body.generateAssembly(assemblyGeneratorVisitor,
                symbolTable));

        return instructions;
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
package frontend.abstractSyntaxTree.typeNodes;


import frontend.abstractSyntaxTree.Node;
import frontend.abstractSyntaxTree.expressions.IdentifierNode;
import frontend.abstractSyntaxTree.statements.StatementNode;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;

public class FunctionDefinitionNode implements Node {
    private final IdentifierNode identifier;
    private final StatementNode body;
    private final ParameterListNode parameters;
    private final TypeNode returnType;

    public FunctionDefinitionNode(TypeNode type, IdentifierNode identifier, ParameterListNode parameters, StatementNode stat) {
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
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
        body.matchReturnType(getReturnType());
        parameters.semanticCheck(symbolTable, errorList);
        body.semanticCheck(symbolTable, errorList);
    }

    // TODO Implement Syntax Error for functions without return/exit or for functions with statements after return/exit

}
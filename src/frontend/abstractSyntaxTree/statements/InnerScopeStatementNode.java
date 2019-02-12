package frontend.abstractSyntaxTree.statements;

import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import frontend.symbolTable.types.Type;

public class InnerScopeStatementNode extends StatementNode {
    private final StatementNode innerStatement;

    public InnerScopeStatementNode(StatementNode innerStatement) {
        this.innerStatement = innerStatement;
    }

    @Override
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
        SymbolTable innerTable = symbolTable.newChild();
        innerStatement.semanticCheck(innerTable, errorList);
        innerStatement.semanticCheck(innerTable, errorList);
    }

    @Override
    public boolean endsWithReturn() {
        return innerStatement.endsWithReturn();
    }

    @Override
    public boolean containsExit() {
        return innerStatement.containsExit();
    }

    @Override
    public boolean containsReturn() {
        return innerStatement.containsReturn();
    }

    @Override
    public void matchReturnType(Type type) {
        innerStatement.matchReturnType(type);
    }
}

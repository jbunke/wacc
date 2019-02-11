package frontend.AbstractSyntaxTree.Statements;


import frontend.AbstractSyntaxTree.Expressions.ExpressionNode;
import frontend.SymbolTable.SemanticError;
import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.BaseTypes;
import frontend.SymbolTable.Types.Type;

public class ExitStatementNode extends StatementNode {
    private final ExpressionNode exitCode;

    public ExitStatementNode(ExpressionNode exitCode) {
        this.exitCode = exitCode;
    }

    @Override
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {

        exitCode.semanticCheck(symbolTable, errorList);

        Type resultType = exitCode.getType(symbolTable);
        if (resultType == null || !resultType.equals(new BaseTypes(BaseTypes.base_types.INT))) {
            errorList.addError(new SemanticError("Can't exit program with non-integer value."));
        }
    }

    @Override
    public boolean endsWithReturn() {
        return true;
    }

    @Override
    public boolean containsExit() {
        return true;
    }
}

package frontend.AbstractSyntaxTree.Expressions;

import frontend.SymbolTable.SemanticError;
import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.Array;
import frontend.SymbolTable.Types.BaseTypes;
import frontend.SymbolTable.Types.Type;

public class UnaryOpExpressionNode extends ExpressionNode {
    private final OperatorType operatorType;
    private final ExpressionNode operand;

    public UnaryOpExpressionNode(String operatorType, ExpressionNode operand) {
        this.operatorType = stringToType(operatorType);
        this.operand = operand;
    }

    private enum OperatorType {
        NOT,
        NEGATIVE,
        POSITIVE,
        LENGTH,
        ORD,
        CHR
    }

    @Override
    public Type getType(SymbolTable symbolTable) {
        switch (operatorType) {
            case NOT:
                return new BaseTypes(BaseTypes.base_types.BOOL);

            case NEGATIVE:
            case POSITIVE:
            case ORD:
            case LENGTH:
                return new BaseTypes(BaseTypes.base_types.INT);

            case CHR:
                return new BaseTypes(BaseTypes.base_types.CHAR);
        }
        return null;
    }

    @Override
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
        Type operandType = operand.getType(symbolTable);
        if (operatorType == OperatorType.LENGTH) {
            if (!(operandType instanceof Array)) {
                errorList.addError(new SemanticError(
                        "expected array type: " +
                                " but received type: \"" + operandType.toString() +
                                "\" for unary operator LENGTH."
                ));
            }
            return;
        }

        Type expected = null;
        switch (operatorType) {
            case NOT:
                expected = new BaseTypes(BaseTypes.base_types.BOOL);
                break;

            case NEGATIVE:
            case POSITIVE:
            case CHR:
                expected = new BaseTypes(BaseTypes.base_types.INT);
                break;
            case ORD:
                expected = new BaseTypes(BaseTypes.base_types.CHAR);
                break;
        }

        if (!operandType.equals(expected)) {
            errorList.addError(new SemanticError(
                    "expected type: \"" + expected.toString() +
                            "\" but given type: \"" + operandType.toString() +
                            "\" for unary operator " + operatorType.toString() + "."
            ));
        }
    }

    private OperatorType stringToType(String operator) {
        switch (operator) {
            case "!":
                return OperatorType.NOT;
            case "+":
                return OperatorType.POSITIVE;
            case "-":
                return OperatorType.NEGATIVE;
            case "len":
                return OperatorType.LENGTH;
            case "ord":
                return OperatorType.ORD;
            case "chr":
                return OperatorType.CHR;
            default:
                throw new IllegalArgumentException();
        }
    }
}

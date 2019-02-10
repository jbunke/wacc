package frontend.AbstractSyntaxTree.Expressions;

import frontend.SymbolTable.SemanticError;
import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.Array;
import frontend.SymbolTable.Types.BaseTypes;
import frontend.SymbolTable.Types.Type;

import java.util.ArrayList;
import java.util.List;

public class BinaryOpExpressionNode extends ExpressionNode {
    private final ExpressionNode left;
    private final ExpressionNode right;
    private final OperatorType operatorType;

    public BinaryOpExpressionNode(ExpressionNode left, String operatorType, ExpressionNode right) {
        this.left = left;
        this.operatorType = stringToType(operatorType);
        this.right = right;
    }

    private enum OperatorType {
        TIMES,
        DIVIDE,
        MOD,
        PLUS,
        MINUS,
        GREATER_THAN,
        GREATER_THAN_OR_EQUAL,
        LESS_THAN,
        LESS_THAN_OR_EQUAL,
        EQUAL,
        NOT_EQUAL,
        AND,
        OR
    }

    @Override
    public void semanticCheck(SymbolTable symbolTable, SemanticErrorList errorList) {
        final Type leftType = left.getType(symbolTable);
        final Type rightType = right.getType(symbolTable);


        if (leftType == null) {
            errorList.addError(new SemanticError(
                    "Invalid type on the left of " + operatorType.toString()
            ));
        } else if (rightType == null) {
            errorList.addError(new SemanticError(
                    "Invalid type on the right of " + operatorType.toString()
            ));
        } else if (!leftType.equals(rightType)) {
            errorList.addError(new SemanticError(
                    "Binary operator \"" + operatorType.toString() + "\" given operands of different types. "
                            + "Types given are \"" + leftType.toString() + "\" and \""
                            + rightType.toString() + "\"."
            ));
        } else {
            List<Type> expected = new ArrayList<>();
            if (operatorType == OperatorType.AND || operatorType == OperatorType.OR) {
                expected.add(new BaseTypes(BaseTypes.base_types.BOOL));
            }

            if (operatorType == OperatorType.TIMES
                    || operatorType == OperatorType.DIVIDE
                    || operatorType == OperatorType.MOD
                    || operatorType == OperatorType.PLUS
                    || operatorType == OperatorType.MINUS
                    || operatorType == OperatorType.LESS_THAN
                    || operatorType == OperatorType.LESS_THAN_OR_EQUAL
                    || operatorType == OperatorType.GREATER_THAN
                    || operatorType == OperatorType.GREATER_THAN_OR_EQUAL
            ) {
                expected.add(new BaseTypes(BaseTypes.base_types.INT));
            }

            if (operatorType == OperatorType.LESS_THAN
                    || operatorType == OperatorType.LESS_THAN_OR_EQUAL
                    || operatorType == OperatorType.GREATER_THAN
                    || operatorType == OperatorType.GREATER_THAN_OR_EQUAL
            ) {
                expected.add(new BaseTypes(BaseTypes.base_types.CHAR));
            }

            boolean found = false;
            for (Type t : expected) {
                if (t.equals(leftType)) {
                    found = true;
                    break;
                }
            }
            if (!(found || operatorType == OperatorType.EQUAL || operatorType == OperatorType.NOT_EQUAL)) {
                errorList.addError(new SemanticError(
                        "Possible expected operands: " + expected.toString() +
                                " but received operands of type: \"" + leftType.toString() +
                                "\" for binary operator: " + operatorType.toString() + "."
                ));
            }
        }

    }

    public Type getType(SymbolTable symbolTable) {

        Type leftType = left.getType(symbolTable);
        Type rightType = right.getType(symbolTable);
        if (leftType == null || rightType == null || !leftType.equals(rightType)) {
            return null;
        }

        switch (operatorType) {
            case GREATER_THAN:
            case GREATER_THAN_OR_EQUAL:
            case LESS_THAN:
            case LESS_THAN_OR_EQUAL:
            case EQUAL:
            case NOT_EQUAL:
            case AND:
            case OR:
                return new BaseTypes(BaseTypes.base_types.BOOL);

            case TIMES:
            case DIVIDE:
            case MOD:
            case PLUS:
            case MINUS:
                return new BaseTypes(BaseTypes.base_types.INT);
            default:
                return null;
        }
    }

    private static OperatorType stringToType(String operator) {
        switch (operator) {
            case "*":
                return OperatorType.TIMES;
            case "/":
                return OperatorType.DIVIDE;
            case "%":
                return OperatorType.MOD;
            case "+":
                return OperatorType.PLUS;
            case "-":
                return OperatorType.MINUS;
            case ">":
                return OperatorType.GREATER_THAN;
            case ">=":
                return OperatorType.GREATER_THAN_OR_EQUAL;
            case "<":
                return OperatorType.LESS_THAN;
            case "<=":
                return OperatorType.LESS_THAN_OR_EQUAL;
            case "==":
                return OperatorType.EQUAL;
            case "!=":
                return OperatorType.NOT_EQUAL;
            case "&&":
                return OperatorType.AND;
            case "||":
                return OperatorType.OR;
            default:
                throw new IllegalArgumentException();
        }
    }
}

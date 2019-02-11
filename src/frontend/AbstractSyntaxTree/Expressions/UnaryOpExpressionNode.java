package frontend.AbstractSyntaxTree.Expressions;

import frontend.SymbolTable.SemanticError;
import frontend.SymbolTable.SemanticErrorList;
import frontend.SymbolTable.SymbolTable;
import frontend.SymbolTable.Types.Array;
import frontend.SymbolTable.Types.BaseTypes;
import frontend.SymbolTable.Types.Pair;
import frontend.SymbolTable.Types.Type;

import java.util.Map;

public class UnaryOpExpressionNode extends ExpressionNode {
    private final static Map<String, OperatorType> stringOpMap = Map.ofEntries(
            Map.entry("!", OperatorType.NOT),
            Map.entry("+", OperatorType.POSITIVE),
            Map.entry("-", OperatorType.NEGATIVE),
            Map.entry("len", OperatorType.LENGTH),
            Map.entry("ord", OperatorType.ORD),
            Map.entry("chr", OperatorType.CHR),
            Map.entry("fst", OperatorType.FST),
            Map.entry("snd", OperatorType.SND)
    );

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
        CHR,
        FST,
        SND
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

            case FST:
                if (!(operand.getType(symbolTable) instanceof Pair)) {

                }
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

        Type expected = getType(null);

        if (!operandType.equals(expected)) {
            errorList.addError(new SemanticError(
                    "expected type: \"" + expected.toString() +
                            "\" but given type: \"" + operandType.toString() +
                            "\" for unary operator " + operatorType.toString() + "."
            ));
        }
    }

    private OperatorType stringToType(String operator) {
        if (stringOpMap.containsKey(operator)) {
            return stringOpMap.get(operator);
        }
        throw new IllegalArgumentException();
    }
}

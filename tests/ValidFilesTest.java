import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

public class ValidFilesTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    private void doesCompileWithErrorCode0(String filepath) {
        WACCCompiler.main(new String[]{filepath});
        exit.expectSystemExitWithStatus(0);
    }

    @Test
    public void binarySortTree() {
        doesCompileWithErrorCode0("res/valid/advanced/binarySortTree.wacc");
    }

    @Test
    public void ticTacToe() {
        doesCompileWithErrorCode0("res/valid/advanced/ticTacToe.wacc");
    }

    @Test
    public void hashTable() {
        doesCompileWithErrorCode0("res/valid/advanced/hashTable.wacc");
    }

    @Test
    public void arrayLength() {
        doesCompileWithErrorCode0("res/valid/array/arrayLength.wacc");
    }

    @Test
    public void arrayEmpty() {
        doesCompileWithErrorCode0("res/valid/array/arrayEmpty.wacc");
    }

    @Test
    public void array() {
        doesCompileWithErrorCode0("res/valid/array/array.wacc");
    }

    @Test
    public void arrayLookup() {
        doesCompileWithErrorCode0("res/valid/array/arrayLookup.wacc");
    }

    @Test
    public void modifyString() {
        doesCompileWithErrorCode0("res/valid/array/modifyString.wacc");
    }

    @Test
    public void printRef() {
        doesCompileWithErrorCode0("res/valid/array/printRef.wacc");
    }

    @Test
    public void arraySimple() {
        doesCompileWithErrorCode0("res/valid/array/arraySimple.wacc");
    }

    @Test
    public void arrayNested() {
        doesCompileWithErrorCode0("res/valid/array/arrayNested.wacc");
    }

    @Test
    public void arrayPrint() {
        doesCompileWithErrorCode0("res/valid/array/arrayPrint.wacc");
    }

    @Test
    public void arrayBasic() {
        doesCompileWithErrorCode0("res/valid/array/arrayBasic.wacc");
    }

    @Test
    public void printNull() {
        doesCompileWithErrorCode0("res/valid/pairs/printNull.wacc");
    }

    @Test
    public void printNullPair() {
        doesCompileWithErrorCode0("res/valid/pairs/printNullPair.wacc");
    }

    @Test
    public void printPair() {
        doesCompileWithErrorCode0("res/valid/pairs/printPair.wacc");
    }

    @Test
    public void nestedPair() {
        doesCompileWithErrorCode0("res/valid/pairs/nestedPair.wacc");
    }

    @Test
    public void createRefPair() {
        doesCompileWithErrorCode0("res/valid/pairs/createRefPair.wacc");
    }

    @Test
    public void free() {
        doesCompileWithErrorCode0("res/valid/pairs/free.wacc");
    }

    @Test
    public void writeSnd() {
        doesCompileWithErrorCode0("res/valid/pairs/writeSnd.wacc");
    }

    @Test
    public void writeFst() {
        doesCompileWithErrorCode0("res/valid/pairs/writeFst.wacc");
    }

    @Test
    public void printPairOfNulls() {
        doesCompileWithErrorCode0("res/valid/pairs/printPairOfNulls.wacc");
    }

    @Test
    public void nullFile() {
        doesCompileWithErrorCode0("res/valid/pairs/null.wacc");
    }

    @Test
    public void createPair02() {
        doesCompileWithErrorCode0("res/valid/pairs/createPair02.wacc");
    }

    @Test
    public void createPair() {
        doesCompileWithErrorCode0("res/valid/pairs/createPair.wacc");
    }

    @Test
    public void checkRefPair() {
        doesCompileWithErrorCode0("res/valid/pairs/checkRefPair.wacc");
    }

    @Test
    public void createPair03() {
        doesCompileWithErrorCode0("res/valid/pairs/createPair03.wacc");
    }

    @Test
    public void readPair() {
        doesCompileWithErrorCode0("res/valid/pairs/readPair.wacc");
    }

    @Test
    public void linkedList() {
        doesCompileWithErrorCode0("res/valid/pairs/linkedList.wacc");
    }

    @Test
    public void if1() {
        doesCompileWithErrorCode0("res/valid/if/if1.wacc");
    }

    @Test
    public void whitespace() {
        doesCompileWithErrorCode0("res/valid/if/whitespace.wacc");
    }

    @Test
    public void ifFalse() {
        doesCompileWithErrorCode0("res/valid/if/ifFalse.wacc");
    }

    @Test
    public void ifBasic() {
        doesCompileWithErrorCode0("res/valid/if/ifBasic.wacc");
    }

    @Test
    public void if6() {
        doesCompileWithErrorCode0("res/valid/if/if6.wacc");
    }

    @Test
    public void ifTrue() {
        doesCompileWithErrorCode0("res/valid/if/ifTrue.wacc");
    }

    @Test
    public void if5() {
        doesCompileWithErrorCode0("res/valid/if/if5.wacc");
    }

    @Test
    public void if4() {
        doesCompileWithErrorCode0("res/valid/if/if4.wacc");
    }

    @Test
    public void if3() {
        doesCompileWithErrorCode0("res/valid/if/if3.wacc");
    }

    @Test
    public void if2() {
        doesCompileWithErrorCode0("res/valid/if/if2.wacc");
    }

    @Test
    public void intJustOverflow() {
        doesCompileWithErrorCode0("res/valid/runtimeErr/integerOverflow/intJustOverflow.wacc");
    }

    @Test
    public void intnegateOverflow() {
        doesCompileWithErrorCode0("res/valid/runtimeErr/integerOverflow/intnegateOverflow.wacc");
    }

    @Test
    public void intmultOverflow() {
        doesCompileWithErrorCode0("res/valid/runtimeErr/integerOverflow/intmultOverflow.wacc");
    }

    @Test
    public void intnegateOverflow3() {
        doesCompileWithErrorCode0("res/valid/runtimeErr/integerOverflow/intnegateOverflow3.wacc");
    }

    @Test
    public void intWayOverflow() {
        doesCompileWithErrorCode0("res/valid/runtimeErr/integerOverflow/intWayOverflow.wacc");
    }

    @Test
    public void intnegateOverflow2() {
        doesCompileWithErrorCode0("res/valid/runtimeErr/integerOverflow/intnegateOverflow2.wacc");
    }

    @Test
    public void intUnderflow() {
        doesCompileWithErrorCode0("res/valid/runtimeErr/integerOverflow/intUnderflow.wacc");
    }

    @Test
    public void intnegateOverflow4() {
        doesCompileWithErrorCode0("res/valid/runtimeErr/integerOverflow/intnegateOverflow4.wacc");
    }

    @Test
    public void readNull2() {
        doesCompileWithErrorCode0("res/valid/runtimeErr/nullDereference/readNull2.wacc");
    }

    @Test
    public void useNull2() {
        doesCompileWithErrorCode0("res/valid/runtimeErr/nullDereference/useNull2.wacc");
    }

    @Test
    public void setNull1() {
        doesCompileWithErrorCode0("res/valid/runtimeErr/nullDereference/setNull1.wacc");
    }

    @Test
    public void freeNull() {
        doesCompileWithErrorCode0("res/valid/runtimeErr/nullDereference/freeNull.wacc");
    }

    @Test
    public void setNull2() {
        doesCompileWithErrorCode0("res/valid/runtimeErr/nullDereference/setNull2.wacc");
    }

    @Test
    public void useNull1() {
        doesCompileWithErrorCode0("res/valid/runtimeErr/nullDereference/useNull1.wacc");
    }

    @Test
    public void readNull1() {
        doesCompileWithErrorCode0("res/valid/runtimeErr/nullDereference/readNull1.wacc");
    }

    @Test
    public void doubleFree() {
        doesCompileWithErrorCode0("res/valid/runtimeErr/doubleFrees/doubleFree.wacc");
    }

    @Test
    public void hiddenDoubleFree() {
        doesCompileWithErrorCode0("res/valid/runtimeErr/doubleFrees/hiddenDoubleFree.wacc");
    }

    @Test
    public void divZero() {
        doesCompileWithErrorCode0("res/valid/runtimeErr/divideByZero/divZero.wacc");
    }

    @Test
    public void divideByZero() {
        doesCompileWithErrorCode0("res/valid/runtimeErr/divideByZero/divideByZero.wacc");
    }

    @Test
    public void modByZero() {
        doesCompileWithErrorCode0("res/valid/runtimeErr/divideByZero/modByZero.wacc");
    }

    @Test
    public void arrayOutOfBoundsWrite() {
        doesCompileWithErrorCode0("res/valid/runtimeErr/arrayOutOfBounds/arrayOutOfBoundsWrite.wacc");
    }

    @Test
    public void arrayNegBounds() {
        doesCompileWithErrorCode0("res/valid/runtimeErr/arrayOutOfBounds/arrayNegBounds.wacc");
    }

    @Test
    public void arrayOutOfBounds() {
        doesCompileWithErrorCode0("res/valid/runtimeErr/arrayOutOfBounds/arrayOutOfBounds.wacc");
    }

    @Test
    public void IOLoop() {
        doesCompileWithErrorCode0("res/valid/IO/IOLoop.wacc");
    }

    @Test
    public void echoPuncChar() {
        doesCompileWithErrorCode0("res/valid/IO/read/echoPuncChar.wacc");
    }

    @Test
    public void echoBigInt() {
        doesCompileWithErrorCode0("res/valid/IO/read/echoBigInt.wacc");
    }

    @Test
    public void echoInt() {
        doesCompileWithErrorCode0("res/valid/IO/read/echoInt.wacc");
    }

    @Test
    public void echoNegInt() {
        doesCompileWithErrorCode0("res/valid/IO/read/echoNegInt.wacc");
    }

    @Test
    public void echoChar() {
        doesCompileWithErrorCode0("res/valid/IO/read/echoChar.wacc");
    }

    @Test
    public void echoBigNegInt() {
        doesCompileWithErrorCode0("res/valid/IO/read/echoBigNegInt.wacc");
    }

    @Test
    public void read() {
        doesCompileWithErrorCode0("res/valid/IO/read/read.wacc");
    }

    @Test
    public void IOSequence() {
        doesCompileWithErrorCode0("res/valid/IO/IOSequence.wacc");
    }

    @Test
    public void println() {
        doesCompileWithErrorCode0("res/valid/IO/print/println.wacc");
    }

    @Test
    public void stringAssignmentWithPrint() {
        doesCompileWithErrorCode0("res/valid/IO/print/stringAssignmentWithPrint.wacc");
    }

    @Test
    public void printEscChar() {
        doesCompileWithErrorCode0("res/valid/IO/print/printEscChar.wacc");
    }

    @Test
    public void multipleStringsAssignment() {
        doesCompileWithErrorCode0("res/valid/IO/print/multipleStringsAssignment.wacc");
    }

    @Test
    public void print() {
        doesCompileWithErrorCode0("res/valid/IO/print/print.wacc");
    }

    @Test
    public void hashInProgram() {
        doesCompileWithErrorCode0("res/valid/IO/print/hashInProgram.wacc");
    }

    @Test
    public void printBool() {
        doesCompileWithErrorCode0("res/valid/IO/print/printBool.wacc");
    }

    @Test
    public void printInt() {
        doesCompileWithErrorCode0("res/valid/IO/print/printInt.wacc");
    }

    @Test
    public void printChar() {
        doesCompileWithErrorCode0("res/valid/IO/print/printChar.wacc");
    }

    @Test
    public void skip() {
        doesCompileWithErrorCode0("res/valid/basic/skip/skip.wacc");
    }

    @Test
    public void comment() {
        doesCompileWithErrorCode0("res/valid/basic/skip/comment.wacc");
    }

    @Test
    public void commentInLine() {
        doesCompileWithErrorCode0("res/valid/basic/skip/commentInLine.wacc");
    }

    @Test
    public void exit1() {
        doesCompileWithErrorCode0("res/valid/basic/exit/exit-1.wacc");
    }

    @Test
    public void exitBasic() {
        doesCompileWithErrorCode0("res/valid/basic/exit/exitBasic.wacc");
    }

    @Test
    public void exitBasic2() {
        doesCompileWithErrorCode0("res/valid/basic/exit/exitBasic2.wacc");
    }

    @Test
    public void exitWrap() {
        doesCompileWithErrorCode0("res/valid/basic/exit/exitWrap.wacc");
    }

    @Test
    public void printAllTypes() {
        doesCompileWithErrorCode0("res/valid/scope/printAllTypes.wacc");
    }

    @Test
    public void scopeSimpleRedefine() {
        doesCompileWithErrorCode0("res/valid/scope/scopeSimpleRedefine.wacc");
    }

    @Test
    public void scopeRedefine() {
        doesCompileWithErrorCode0("res/valid/scope/scopeRedefine.wacc");
    }

    @Test
    public void indentationNotImportant() {
        doesCompileWithErrorCode0("res/valid/scope/indentationNotImportant.wacc");
    }

    @Test
    public void scope() {
        doesCompileWithErrorCode0("res/valid/scope/scope.wacc");
    }

    @Test
    public void scopeBasic() {
        doesCompileWithErrorCode0("res/valid/scope/scopeBasic.wacc");
    }

    @Test
    public void ifNested2() {
        doesCompileWithErrorCode0("res/valid/scope/ifNested2.wacc");
    }

    @Test
    public void ifNested1() {
        doesCompileWithErrorCode0("res/valid/scope/ifNested1.wacc");
    }

    @Test
    public void scopeVars() {
        doesCompileWithErrorCode0("res/valid/scope/scopeVars.wacc");
    }

    @Test
    public void intsAndKeywords() {
        doesCompileWithErrorCode0("res/valid/scope/intsAndKeywords.wacc");
    }

    @Test
    public void fixedPointRealArithmetic() {
        doesCompileWithErrorCode0("res/valid/function/nested_functions/fixedPointRealArithmetic.wacc");
    }

    @Test
    public void printTriangle() {
        doesCompileWithErrorCode0("res/valid/function/nested_functions/printTriangle.wacc");
    }

    @Test
    public void printInputTriangle() {
        doesCompileWithErrorCode0("res/valid/function/nested_functions/printInputTriangle.wacc");
    }

    @Test
    public void mutualRecursion() {
        doesCompileWithErrorCode0("res/valid/function/nested_functions/mutualRecursion.wacc");
    }

    @Test
    public void fibonacciFullRec() {
        doesCompileWithErrorCode0("res/valid/function/nested_functions/fibonacciFullRec.wacc");
    }

    @Test
    public void functionConditionalReturn() {
        doesCompileWithErrorCode0("res/valid/function/nested_functions/functionConditionalReturn.wacc");
    }

    @Test
    public void simpleRecursion() {
        doesCompileWithErrorCode0("res/valid/function/nested_functions/simpleRecursion.wacc");
    }

    @Test
    public void fibonacciRecursive() {
        doesCompileWithErrorCode0("res/valid/function/nested_functions/fibonacciRecursive.wacc");
    }

    @Test
    public void functionDeclaration() {
        doesCompileWithErrorCode0("res/valid/function/simple_functions/functionDeclaration.wacc");
    }

    @Test
    public void sameArgName2() {
        doesCompileWithErrorCode0("res/valid/function/simple_functions/sameArgName2.wacc");
    }

    @Test
    public void functionManyArguments() {
        doesCompileWithErrorCode0("res/valid/function/simple_functions/functionManyArguments.wacc");
    }

    @Test
    public void asciiTable() {
        doesCompileWithErrorCode0("res/valid/function/simple_functions/asciiTable.wacc");
    }

    @Test
    public void functionSimple() {
        doesCompileWithErrorCode0("res/valid/function/simple_functions/functionSimple.wacc");
    }

    @Test
    public void incFunction() {
        doesCompileWithErrorCode0("res/valid/function/simple_functions/incFunction.wacc");
    }

    @Test
    public void sameArgName() {
        doesCompileWithErrorCode0("res/valid/function/simple_functions/sameArgName.wacc");
    }

    @Test
    public void negFunction() {
        doesCompileWithErrorCode0("res/valid/function/simple_functions/negFunction.wacc");
    }

    @Test
    public void functionUpdateParameter() {
        doesCompileWithErrorCode0("res/valid/function/simple_functions/functionUpdateParameter.wacc");
    }

    @Test
    public void functionReturnPair() {
        doesCompileWithErrorCode0("res/valid/function/simple_functions/functionReturnPair.wacc");
    }

    @Test
    public void capCharDeclaration() {
        doesCompileWithErrorCode0("res/valid/variables/capCharDeclaration.wacc");
    }

    @Test
    public void negIntDeclaration() {
        doesCompileWithErrorCode0("res/valid/variables/negIntDeclaration.wacc");
    }

    @Test
    public void charDeclaration() {
        doesCompileWithErrorCode0("res/valid/variables/charDeclaration.wacc");
    }

    @Test
    public void emptyStringDeclaration() {
        doesCompileWithErrorCode0("res/valid/variables/emptyStringDeclaration.wacc");
    }

    @Test
    public void stringDeclaration() {
        doesCompileWithErrorCode0("res/valid/variables/stringDeclaration.wacc");
    }

    @Test
    public void longVarNames() {
        doesCompileWithErrorCode0("res/valid/variables/longVarNames.wacc");
    }

    @Test
    public void boolDeclaration() {
        doesCompileWithErrorCode0("res/valid/variables/boolDeclaration.wacc");
    }

    @Test
    public void _VarNames() {
        doesCompileWithErrorCode0("res/valid/variables/_VarNames.wacc");
    }

    @Test
    public void charDeclaration2() {
        doesCompileWithErrorCode0("res/valid/variables/charDeclaration2.wacc");
    }

    @Test
    public void manyVariables() {
        doesCompileWithErrorCode0("res/valid/variables/manyVariables.wacc");
    }

    @Test
    public void intDeclaration() {
        doesCompileWithErrorCode0("res/valid/variables/intDeclaration.wacc");
    }

    @Test
    public void boolDeclaration2() {
        doesCompileWithErrorCode0("res/valid/variables/boolDeclaration2.wacc");
    }

    @Test
    public void zeroIntDeclaration() {
        doesCompileWithErrorCode0("res/valid/variables/zeroIntDeclaration.wacc");
    }

    @Test
    public void puncCharDeclaration() {
        doesCompileWithErrorCode0("res/valid/variables/puncCharDeclaration.wacc");
    }

    @Test
    public void intCalc() {
        doesCompileWithErrorCode0("res/valid/expressions/intCalc.wacc");
    }

    @Test
    public void multNoWhitespaceExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/multNoWhitespaceExpr.wacc");
    }

    @Test
    public void andExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/andExpr.wacc");
    }

    @Test
    public void divExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/divExpr.wacc");
    }

    @Test
    public void negBothMod() {
        doesCompileWithErrorCode0("res/valid/expressions/negBothMod.wacc");
    }

    @Test
    public void longSplitExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/longSplitExpr.wacc");
    }

    @Test
    public void negDivisorDiv() {
        doesCompileWithErrorCode0("res/valid/expressions/negDivisorDiv.wacc");
    }

    @Test
    public void ordAndchrExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/ordAndchrExpr.wacc");
    }

    @Test
    public void lessExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/lessExpr.wacc");
    }

    @Test
    public void lessCharExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/lessCharExpr.wacc");
    }

    @Test
    public void negDividendDiv() {
        doesCompileWithErrorCode0("res/valid/expressions/negDividendDiv.wacc");
    }

    @Test
    public void longExpr2() {
        doesCompileWithErrorCode0("res/valid/expressions/longExpr2.wacc");
    }

    @Test
    public void longSplitExpr2() {
        doesCompileWithErrorCode0("res/valid/expressions/longSplitExpr2.wacc");
    }

    @Test
    public void minusExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/minusExpr.wacc");
    }

    @Test
    public void intExpr1() {
        doesCompileWithErrorCode0("res/valid/expressions/intExpr1.wacc");
    }

    @Test
    public void greaterEqExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/greaterEqExpr.wacc");
    }

    @Test
    public void plusPlusExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/plusPlusExpr.wacc");
    }

    @Test
    public void lessEqExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/lessEqExpr.wacc");
    }

    @Test
    public void multExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/multExpr.wacc");
    }

    @Test
    public void longExpr3() {
        doesCompileWithErrorCode0("res/valid/expressions/longExpr3.wacc");
    }

    @Test
    public void plusNoWhitespaceExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/plusNoWhitespaceExpr.wacc");
    }

    @Test
    public void plusMinusExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/plusMinusExpr.wacc");
    }

    @Test
    public void negDividendMod() {
        doesCompileWithErrorCode0("res/valid/expressions/negDividendMod.wacc");
    }

    @Test
    public void longExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/longExpr.wacc");
    }

    @Test
    public void boolExpr1() {
        doesCompileWithErrorCode0("res/valid/expressions/boolExpr1.wacc");
    }

    @Test
    public void equalsExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/equalsExpr.wacc");
    }

    @Test
    public void negDivisorMod() {
        doesCompileWithErrorCode0("res/valid/expressions/negDivisorMod.wacc");
    }

    @Test
    public void negBothDiv() {
        doesCompileWithErrorCode0("res/valid/expressions/negBothDiv.wacc");
    }

    @Test
    public void orExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/orExpr.wacc");
    }

    @Test
    public void sequentialCount() {
        doesCompileWithErrorCode0("res/valid/expressions/sequentialCount.wacc");
    }

    @Test
    public void notequalsExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/notequalsExpr.wacc");
    }

    @Test
    public void negExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/negExpr.wacc");
    }

    @Test
    public void stringEqualsExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/stringEqualsExpr.wacc");
    }

    @Test
    public void notExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/notExpr.wacc");
    }

    @Test
    public void greaterExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/greaterExpr.wacc");
    }

    @Test
    public void plusExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/plusExpr.wacc");
    }

    @Test
    public void charComparisonExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/charComparisonExpr.wacc");
    }

    @Test
    public void modExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/modExpr.wacc");
    }

    @Test
    public void minusPlusExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/minusPlusExpr.wacc");
    }

    @Test
    public void minusMinusExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/minusMinusExpr.wacc");
    }

    @Test
    public void boolCalc() {
        doesCompileWithErrorCode0("res/valid/expressions/boolCalc.wacc");
    }

    @Test
    public void minusNoWhitespaceExpr() {
        doesCompileWithErrorCode0("res/valid/expressions/minusNoWhitespaceExpr.wacc");
    }

    @Test
    public void stringAssignment() {
        doesCompileWithErrorCode0("res/valid/sequence/stringAssignment.wacc");
    }

    @Test
    public void charAssignment() {
        doesCompileWithErrorCode0("res/valid/sequence/charAssignment.wacc");
    }

    @Test
    public void intLeadingZeros() {
        doesCompileWithErrorCode0("res/valid/sequence/intLeadingZeros.wacc");
    }

    @Test
    public void boolAssignment() {
        doesCompileWithErrorCode0("res/valid/sequence/boolAssignment.wacc");
    }

    @Test
    public void intAssignment() {
        doesCompileWithErrorCode0("res/valid/sequence/intAssignment.wacc");
    }

    @Test
    public void basicSeq() {
        doesCompileWithErrorCode0("res/valid/sequence/basicSeq.wacc");
    }

    @Test
    public void basicSeq2() {
        doesCompileWithErrorCode0("res/valid/sequence/basicSeq2.wacc");
    }

    @Test
    public void exitSimple() {
        doesCompileWithErrorCode0("res/valid/sequence/exitSimple.wacc");
    }

    @Test
    public void whileBasic() {
        doesCompileWithErrorCode0("res/valid/while/whileBasic.wacc");
    }

    @Test
    public void loopIntCondition() {
        doesCompileWithErrorCode0("res/valid/while/loopIntCondition.wacc");
    }

    @Test
    public void rmStyleAddIO() {
        doesCompileWithErrorCode0("res/valid/while/rmStyleAddIO.wacc");
    }

    @Test
    public void whileFalse() {
        doesCompileWithErrorCode0("res/valid/while/whileFalse.wacc");
    }

    @Test
    public void fibonacciFullIt() {
        doesCompileWithErrorCode0("res/valid/while/fibonacciFullIt.wacc");
    }

    @Test
    public void whileCount() {
        doesCompileWithErrorCode0("res/valid/while/whileCount.wacc");
    }

    @Test
    public void min() {
        doesCompileWithErrorCode0("res/valid/while/min.wacc");
    }

    @Test
    public void max() {
        doesCompileWithErrorCode0("res/valid/while/max.wacc");
    }

    @Test
    public void whileBoolFlip() {
        doesCompileWithErrorCode0("res/valid/while/whileBoolFlip.wacc");
    }

    @Test
    public void fibonacciIterative() {
        doesCompileWithErrorCode0("res/valid/while/fibonacciIterative.wacc");
    }

    @Test
    public void loopCharCondition() {
        doesCompileWithErrorCode0("res/valid/while/loopCharCondition.wacc");
    }

    @Test
    public void rmStyleAdd() {
        doesCompileWithErrorCode0("res/valid/while/rmStyleAdd.wacc");
    }

}
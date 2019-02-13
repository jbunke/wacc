import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

public class ValidFilesTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    private void doesCompileWithCorrectErrorCode(String filepath) {
        WACCCompiler.main(new String[]{filepath});
        exit.expectSystemExitWithStatus(0);
    }

    @Test
    public void binarySortTree() {
        doesCompileWithCorrectErrorCode("res/valid/advanced/binarySortTree.wacc");
    }

    @Test
    public void ticTacToe() {
        doesCompileWithCorrectErrorCode("res/valid/advanced/ticTacToe.wacc");
    }

    @Test
    public void hashTable() {
        doesCompileWithCorrectErrorCode("res/valid/advanced/hashTable.wacc");
    }

    @Test
    public void arrayLength() {
        doesCompileWithCorrectErrorCode("res/valid/array/arrayLength.wacc");
    }

    @Test
    public void arrayEmpty() {
        doesCompileWithCorrectErrorCode("res/valid/array/arrayEmpty.wacc");
    }

    @Test
    public void array() {
        doesCompileWithCorrectErrorCode("res/valid/array/array.wacc");
    }

    @Test
    public void arrayLookup() {
        doesCompileWithCorrectErrorCode("res/valid/array/arrayLookup.wacc");
    }

    @Test
    public void modifyString() {
        doesCompileWithCorrectErrorCode("res/valid/array/modifyString.wacc");
    }

    @Test
    public void printRef() {
        doesCompileWithCorrectErrorCode("res/valid/array/printRef.wacc");
    }

    @Test
    public void arraySimple() {
        doesCompileWithCorrectErrorCode("res/valid/array/arraySimple.wacc");
    }

    @Test
    public void arrayNested() {
        doesCompileWithCorrectErrorCode("res/valid/array/arrayNested.wacc");
    }

    @Test
    public void arrayPrint() {
        doesCompileWithCorrectErrorCode("res/valid/array/arrayPrint.wacc");
    }

    @Test
    public void arrayBasic() {
        doesCompileWithCorrectErrorCode("res/valid/array/arrayBasic.wacc");
    }

    @Test
    public void printNull() {
        doesCompileWithCorrectErrorCode("res/valid/pairs/printNull.wacc");
    }

    @Test
    public void printNullPair() {
        doesCompileWithCorrectErrorCode("res/valid/pairs/printNullPair.wacc");
    }

    @Test
    public void printPair() {
        doesCompileWithCorrectErrorCode("res/valid/pairs/printPair.wacc");
    }

    @Test
    public void nestedPair() {
        doesCompileWithCorrectErrorCode("res/valid/pairs/nestedPair.wacc");
    }

    @Test
    public void createRefPair() {
        doesCompileWithCorrectErrorCode("res/valid/pairs/createRefPair.wacc");
    }

    @Test
    public void free() {
        doesCompileWithCorrectErrorCode("res/valid/pairs/free.wacc");
    }

    @Test
    public void writeSnd() {
        doesCompileWithCorrectErrorCode("res/valid/pairs/writeSnd.wacc");
    }

    @Test
    public void writeFst() {
        doesCompileWithCorrectErrorCode("res/valid/pairs/writeFst.wacc");
    }

    @Test
    public void printPairOfNulls() {
        doesCompileWithCorrectErrorCode("res/valid/pairs/printPairOfNulls.wacc");
    }

    @Test
    public void nullFile() {
        doesCompileWithCorrectErrorCode("res/valid/pairs/null.wacc");
    }

    @Test
    public void createPair02() {
        doesCompileWithCorrectErrorCode("res/valid/pairs/createPair02.wacc");
    }

    @Test
    public void createPair() {
        doesCompileWithCorrectErrorCode("res/valid/pairs/createPair.wacc");
    }

    @Test
    public void checkRefPair() {
        doesCompileWithCorrectErrorCode("res/valid/pairs/checkRefPair.wacc");
    }

    @Test
    public void createPair03() {
        doesCompileWithCorrectErrorCode("res/valid/pairs/createPair03.wacc");
    }

    @Test
    public void readPair() {
        doesCompileWithCorrectErrorCode("res/valid/pairs/readPair.wacc");
    }

    @Test
    public void linkedList() {
        doesCompileWithCorrectErrorCode("res/valid/pairs/linkedList.wacc");
    }

    @Test
    public void if1() {
        doesCompileWithCorrectErrorCode("res/valid/if/if1.wacc");
    }

    @Test
    public void whitespace() {
        doesCompileWithCorrectErrorCode("res/valid/if/whitespace.wacc");
    }

    @Test
    public void ifFalse() {
        doesCompileWithCorrectErrorCode("res/valid/if/ifFalse.wacc");
    }

    @Test
    public void ifBasic() {
        doesCompileWithCorrectErrorCode("res/valid/if/ifBasic.wacc");
    }

    @Test
    public void if6() {
        doesCompileWithCorrectErrorCode("res/valid/if/if6.wacc");
    }

    @Test
    public void ifTrue() {
        doesCompileWithCorrectErrorCode("res/valid/if/ifTrue.wacc");
    }

    @Test
    public void if5() {
        doesCompileWithCorrectErrorCode("res/valid/if/if5.wacc");
    }

    @Test
    public void if4() {
        doesCompileWithCorrectErrorCode("res/valid/if/if4.wacc");
    }

    @Test
    public void if3() {
        doesCompileWithCorrectErrorCode("res/valid/if/if3.wacc");
    }

    @Test
    public void if2() {
        doesCompileWithCorrectErrorCode("res/valid/if/if2.wacc");
    }

    @Test
    public void intJustOverflow() {
        doesCompileWithCorrectErrorCode("res/valid/runtimeErr/integerOverflow/intJustOverflow.wacc");
    }

    @Test
    public void intnegateOverflow() {
        doesCompileWithCorrectErrorCode("res/valid/runtimeErr/integerOverflow/intnegateOverflow.wacc");
    }

    @Test
    public void intmultOverflow() {
        doesCompileWithCorrectErrorCode("res/valid/runtimeErr/integerOverflow/intmultOverflow.wacc");
    }

    @Test
    public void intnegateOverflow3() {
        doesCompileWithCorrectErrorCode("res/valid/runtimeErr/integerOverflow/intnegateOverflow3.wacc");
    }

    @Test
    public void intWayOverflow() {
        doesCompileWithCorrectErrorCode("res/valid/runtimeErr/integerOverflow/intWayOverflow.wacc");
    }

    @Test
    public void intnegateOverflow2() {
        doesCompileWithCorrectErrorCode("res/valid/runtimeErr/integerOverflow/intnegateOverflow2.wacc");
    }

    @Test
    public void intUnderflow() {
        doesCompileWithCorrectErrorCode("res/valid/runtimeErr/integerOverflow/intUnderflow.wacc");
    }

    @Test
    public void intnegateOverflow4() {
        doesCompileWithCorrectErrorCode("res/valid/runtimeErr/integerOverflow/intnegateOverflow4.wacc");
    }

    @Test
    public void readNull2() {
        doesCompileWithCorrectErrorCode("res/valid/runtimeErr/nullDereference/readNull2.wacc");
    }

    @Test
    public void useNull2() {
        doesCompileWithCorrectErrorCode("res/valid/runtimeErr/nullDereference/useNull2.wacc");
    }

    @Test
    public void setNull1() {
        doesCompileWithCorrectErrorCode("res/valid/runtimeErr/nullDereference/setNull1.wacc");
    }

    @Test
    public void freeNull() {
        doesCompileWithCorrectErrorCode("res/valid/runtimeErr/nullDereference/freeNull.wacc");
    }

    @Test
    public void setNull2() {
        doesCompileWithCorrectErrorCode("res/valid/runtimeErr/nullDereference/setNull2.wacc");
    }

    @Test
    public void useNull1() {
        doesCompileWithCorrectErrorCode("res/valid/runtimeErr/nullDereference/useNull1.wacc");
    }

    @Test
    public void readNull1() {
        doesCompileWithCorrectErrorCode("res/valid/runtimeErr/nullDereference/readNull1.wacc");
    }

    @Test
    public void doubleFree() {
        doesCompileWithCorrectErrorCode("res/valid/runtimeErr/doubleFrees/doubleFree.wacc");
    }

    @Test
    public void hiddenDoubleFree() {
        doesCompileWithCorrectErrorCode("res/valid/runtimeErr/doubleFrees/hiddenDoubleFree.wacc");
    }

    @Test
    public void divZero() {
        doesCompileWithCorrectErrorCode("res/valid/runtimeErr/divideByZero/divZero.wacc");
    }

    @Test
    public void divideByZero() {
        doesCompileWithCorrectErrorCode("res/valid/runtimeErr/divideByZero/divideByZero.wacc");
    }

    @Test
    public void modByZero() {
        doesCompileWithCorrectErrorCode("res/valid/runtimeErr/divideByZero/modByZero.wacc");
    }

    @Test
    public void arrayOutOfBoundsWrite() {
        doesCompileWithCorrectErrorCode("res/valid/runtimeErr/arrayOutOfBounds/arrayOutOfBoundsWrite.wacc");
    }

    @Test
    public void arrayNegBounds() {
        doesCompileWithCorrectErrorCode("res/valid/runtimeErr/arrayOutOfBounds/arrayNegBounds.wacc");
    }

    @Test
    public void arrayOutOfBounds() {
        doesCompileWithCorrectErrorCode("res/valid/runtimeErr/arrayOutOfBounds/arrayOutOfBounds.wacc");
    }

    @Test
    public void IOLoop() {
        doesCompileWithCorrectErrorCode("res/valid/IO/IOLoop.wacc");
    }

    @Test
    public void echoPuncChar() {
        doesCompileWithCorrectErrorCode("res/valid/IO/read/echoPuncChar.wacc");
    }

    @Test
    public void echoBigInt() {
        doesCompileWithCorrectErrorCode("res/valid/IO/read/echoBigInt.wacc");
    }

    @Test
    public void echoInt() {
        doesCompileWithCorrectErrorCode("res/valid/IO/read/echoInt.wacc");
    }

    @Test
    public void echoNegInt() {
        doesCompileWithCorrectErrorCode("res/valid/IO/read/echoNegInt.wacc");
    }

    @Test
    public void echoChar() {
        doesCompileWithCorrectErrorCode("res/valid/IO/read/echoChar.wacc");
    }

    @Test
    public void echoBigNegInt() {
        doesCompileWithCorrectErrorCode("res/valid/IO/read/echoBigNegInt.wacc");
    }

    @Test
    public void read() {
        doesCompileWithCorrectErrorCode("res/valid/IO/read/read.wacc");
    }

    @Test
    public void IOSequence() {
        doesCompileWithCorrectErrorCode("res/valid/IO/IOSequence.wacc");
    }

    @Test
    public void println() {
        doesCompileWithCorrectErrorCode("res/valid/IO/print/println.wacc");
    }

    @Test
    public void stringAssignmentWithPrint() {
        doesCompileWithCorrectErrorCode("res/valid/IO/print/stringAssignmentWithPrint.wacc");
    }

    @Test
    public void printEscChar() {
        doesCompileWithCorrectErrorCode("res/valid/IO/print/printEscChar.wacc");
    }

    @Test
    public void multipleStringsAssignment() {
        doesCompileWithCorrectErrorCode("res/valid/IO/print/multipleStringsAssignment.wacc");
    }

    @Test
    public void print() {
        doesCompileWithCorrectErrorCode("res/valid/IO/print/print.wacc");
    }

    @Test
    public void hashInProgram() {
        doesCompileWithCorrectErrorCode("res/valid/IO/print/hashInProgram.wacc");
    }

    @Test
    public void printBool() {
        doesCompileWithCorrectErrorCode("res/valid/IO/print/printBool.wacc");
    }

    @Test
    public void printInt() {
        doesCompileWithCorrectErrorCode("res/valid/IO/print/printInt.wacc");
    }

    @Test
    public void printChar() {
        doesCompileWithCorrectErrorCode("res/valid/IO/print/printChar.wacc");
    }

    @Test
    public void skip() {
        doesCompileWithCorrectErrorCode("res/valid/basic/skip/skip.wacc");
    }

    @Test
    public void comment() {
        doesCompileWithCorrectErrorCode("res/valid/basic/skip/comment.wacc");
    }

    @Test
    public void commentInLine() {
        doesCompileWithCorrectErrorCode("res/valid/basic/skip/commentInLine.wacc");
    }

    @Test
    public void exit1() {
        doesCompileWithCorrectErrorCode("res/valid/basic/exit/exit-1.wacc");
    }

    @Test
    public void exitBasic() {
        doesCompileWithCorrectErrorCode("res/valid/basic/exit/exitBasic.wacc");
    }

    @Test
    public void exitBasic2() {
        doesCompileWithCorrectErrorCode("res/valid/basic/exit/exitBasic2.wacc");
    }

    @Test
    public void exitWrap() {
        doesCompileWithCorrectErrorCode("res/valid/basic/exit/exitWrap.wacc");
    }

    @Test
    public void printAllTypes() {
        doesCompileWithCorrectErrorCode("res/valid/scope/printAllTypes.wacc");
    }

    @Test
    public void scopeSimpleRedefine() {
        doesCompileWithCorrectErrorCode("res/valid/scope/scopeSimpleRedefine.wacc");
    }

    @Test
    public void scopeRedefine() {
        doesCompileWithCorrectErrorCode("res/valid/scope/scopeRedefine.wacc");
    }

    @Test
    public void indentationNotImportant() {
        doesCompileWithCorrectErrorCode("res/valid/scope/indentationNotImportant.wacc");
    }

    @Test
    public void scope() {
        doesCompileWithCorrectErrorCode("res/valid/scope/scope.wacc");
    }

    @Test
    public void scopeBasic() {
        doesCompileWithCorrectErrorCode("res/valid/scope/scopeBasic.wacc");
    }

    @Test
    public void ifNested2() {
        doesCompileWithCorrectErrorCode("res/valid/scope/ifNested2.wacc");
    }

    @Test
    public void ifNested1() {
        doesCompileWithCorrectErrorCode("res/valid/scope/ifNested1.wacc");
    }

    @Test
    public void scopeVars() {
        doesCompileWithCorrectErrorCode("res/valid/scope/scopeVars.wacc");
    }

    @Test
    public void intsAndKeywords() {
        doesCompileWithCorrectErrorCode("res/valid/scope/intsAndKeywords.wacc");
    }

    @Test
    public void fixedPointRealArithmetic() {
        doesCompileWithCorrectErrorCode("res/valid/function/nested_functions/fixedPointRealArithmetic.wacc");
    }

    @Test
    public void printTriangle() {
        doesCompileWithCorrectErrorCode("res/valid/function/nested_functions/printTriangle.wacc");
    }

    @Test
    public void printInputTriangle() {
        doesCompileWithCorrectErrorCode("res/valid/function/nested_functions/printInputTriangle.wacc");
    }

    @Test
    public void mutualRecursion() {
        doesCompileWithCorrectErrorCode("res/valid/function/nested_functions/mutualRecursion.wacc");
    }

    @Test
    public void fibonacciFullRec() {
        doesCompileWithCorrectErrorCode("res/valid/function/nested_functions/fibonacciFullRec.wacc");
    }

    @Test
    public void functionConditionalReturn() {
        doesCompileWithCorrectErrorCode("res/valid/function/nested_functions/functionConditionalReturn.wacc");
    }

    @Test
    public void simpleRecursion() {
        doesCompileWithCorrectErrorCode("res/valid/function/nested_functions/simpleRecursion.wacc");
    }

    @Test
    public void fibonacciRecursive() {
        doesCompileWithCorrectErrorCode("res/valid/function/nested_functions/fibonacciRecursive.wacc");
    }

    @Test
    public void functionDeclaration() {
        doesCompileWithCorrectErrorCode("res/valid/function/simple_functions/functionDeclaration.wacc");
    }

    @Test
    public void sameArgName2() {
        doesCompileWithCorrectErrorCode("res/valid/function/simple_functions/sameArgName2.wacc");
    }

    @Test
    public void functionManyArguments() {
        doesCompileWithCorrectErrorCode("res/valid/function/simple_functions/functionManyArguments.wacc");
    }

    @Test
    public void asciiTable() {
        doesCompileWithCorrectErrorCode("res/valid/function/simple_functions/asciiTable.wacc");
    }

    @Test
    public void functionSimple() {
        doesCompileWithCorrectErrorCode("res/valid/function/simple_functions/functionSimple.wacc");
    }

    @Test
    public void incFunction() {
        doesCompileWithCorrectErrorCode("res/valid/function/simple_functions/incFunction.wacc");
    }

    @Test
    public void sameArgName() {
        doesCompileWithCorrectErrorCode("res/valid/function/simple_functions/sameArgName.wacc");
    }

    @Test
    public void negFunction() {
        doesCompileWithCorrectErrorCode("res/valid/function/simple_functions/negFunction.wacc");
    }

    @Test
    public void functionUpdateParameter() {
        doesCompileWithCorrectErrorCode("res/valid/function/simple_functions/functionUpdateParameter.wacc");
    }

    @Test
    public void functionReturnPair() {
        doesCompileWithCorrectErrorCode("res/valid/function/simple_functions/functionReturnPair.wacc");
    }

    @Test
    public void capCharDeclaration() {
        doesCompileWithCorrectErrorCode("res/valid/variables/capCharDeclaration.wacc");
    }

    @Test
    public void negIntDeclaration() {
        doesCompileWithCorrectErrorCode("res/valid/variables/negIntDeclaration.wacc");
    }

    @Test
    public void charDeclaration() {
        doesCompileWithCorrectErrorCode("res/valid/variables/charDeclaration.wacc");
    }

    @Test
    public void emptyStringDeclaration() {
        doesCompileWithCorrectErrorCode("res/valid/variables/emptyStringDeclaration.wacc");
    }

    @Test
    public void stringDeclaration() {
        doesCompileWithCorrectErrorCode("res/valid/variables/stringDeclaration.wacc");
    }

    @Test
    public void longVarNames() {
        doesCompileWithCorrectErrorCode("res/valid/variables/longVarNames.wacc");
    }

    @Test
    public void boolDeclaration() {
        doesCompileWithCorrectErrorCode("res/valid/variables/boolDeclaration.wacc");
    }

    @Test
    public void _VarNames() {
        doesCompileWithCorrectErrorCode("res/valid/variables/_VarNames.wacc");
    }

    @Test
    public void charDeclaration2() {
        doesCompileWithCorrectErrorCode("res/valid/variables/charDeclaration2.wacc");
    }

    @Test
    public void manyVariables() {
        doesCompileWithCorrectErrorCode("res/valid/variables/manyVariables.wacc");
    }

    @Test
    public void intDeclaration() {
        doesCompileWithCorrectErrorCode("res/valid/variables/intDeclaration.wacc");
    }

    @Test
    public void boolDeclaration2() {
        doesCompileWithCorrectErrorCode("res/valid/variables/boolDeclaration2.wacc");
    }

    @Test
    public void zeroIntDeclaration() {
        doesCompileWithCorrectErrorCode("res/valid/variables/zeroIntDeclaration.wacc");
    }

    @Test
    public void puncCharDeclaration() {
        doesCompileWithCorrectErrorCode("res/valid/variables/puncCharDeclaration.wacc");
    }

    @Test
    public void intCalc() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/intCalc.wacc");
    }

    @Test
    public void multNoWhitespaceExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/multNoWhitespaceExpr.wacc");
    }

    @Test
    public void andExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/andExpr.wacc");
    }

    @Test
    public void divExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/divExpr.wacc");
    }

    @Test
    public void negBothMod() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/negBothMod.wacc");
    }

    @Test
    public void longSplitExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/longSplitExpr.wacc");
    }

    @Test
    public void negDivisorDiv() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/negDivisorDiv.wacc");
    }

    @Test
    public void ordAndchrExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/ordAndchrExpr.wacc");
    }

    @Test
    public void lessExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/lessExpr.wacc");
    }

    @Test
    public void lessCharExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/lessCharExpr.wacc");
    }

    @Test
    public void negDividendDiv() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/negDividendDiv.wacc");
    }

    @Test
    public void longExpr2() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/longExpr2.wacc");
    }

    @Test
    public void longSplitExpr2() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/longSplitExpr2.wacc");
    }

    @Test
    public void minusExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/minusExpr.wacc");
    }

    @Test
    public void intExpr1() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/intExpr1.wacc");
    }

    @Test
    public void greaterEqExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/greaterEqExpr.wacc");
    }

    @Test
    public void plusPlusExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/plusPlusExpr.wacc");
    }

    @Test
    public void lessEqExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/lessEqExpr.wacc");
    }

    @Test
    public void multExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/multExpr.wacc");
    }

    @Test
    public void longExpr3() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/longExpr3.wacc");
    }

    @Test
    public void plusNoWhitespaceExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/plusNoWhitespaceExpr.wacc");
    }

    @Test
    public void plusMinusExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/plusMinusExpr.wacc");
    }

    @Test
    public void negDividendMod() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/negDividendMod.wacc");
    }

    @Test
    public void longExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/longExpr.wacc");
    }

    @Test
    public void boolExpr1() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/boolExpr1.wacc");
    }

    @Test
    public void equalsExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/equalsExpr.wacc");
    }

    @Test
    public void negDivisorMod() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/negDivisorMod.wacc");
    }

    @Test
    public void negBothDiv() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/negBothDiv.wacc");
    }

    @Test
    public void orExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/orExpr.wacc");
    }

    @Test
    public void sequentialCount() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/sequentialCount.wacc");
    }

    @Test
    public void notequalsExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/notequalsExpr.wacc");
    }

    @Test
    public void negExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/negExpr.wacc");
    }

    @Test
    public void stringEqualsExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/stringEqualsExpr.wacc");
    }

    @Test
    public void notExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/notExpr.wacc");
    }

    @Test
    public void greaterExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/greaterExpr.wacc");
    }

    @Test
    public void plusExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/plusExpr.wacc");
    }

    @Test
    public void charComparisonExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/charComparisonExpr.wacc");
    }

    @Test
    public void modExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/modExpr.wacc");
    }

    @Test
    public void minusPlusExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/minusPlusExpr.wacc");
    }

    @Test
    public void minusMinusExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/minusMinusExpr.wacc");
    }

    @Test
    public void boolCalc() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/boolCalc.wacc");
    }

    @Test
    public void minusNoWhitespaceExpr() {
        doesCompileWithCorrectErrorCode("res/valid/expressions/minusNoWhitespaceExpr.wacc");
    }

    @Test
    public void stringAssignment() {
        doesCompileWithCorrectErrorCode("res/valid/sequence/stringAssignment.wacc");
    }

    @Test
    public void charAssignment() {
        doesCompileWithCorrectErrorCode("res/valid/sequence/charAssignment.wacc");
    }

    @Test
    public void intLeadingZeros() {
        doesCompileWithCorrectErrorCode("res/valid/sequence/intLeadingZeros.wacc");
    }

    @Test
    public void boolAssignment() {
        doesCompileWithCorrectErrorCode("res/valid/sequence/boolAssignment.wacc");
    }

    @Test
    public void intAssignment() {
        doesCompileWithCorrectErrorCode("res/valid/sequence/intAssignment.wacc");
    }

    @Test
    public void basicSeq() {
        doesCompileWithCorrectErrorCode("res/valid/sequence/basicSeq.wacc");
    }

    @Test
    public void basicSeq2() {
        doesCompileWithCorrectErrorCode("res/valid/sequence/basicSeq2.wacc");
    }

    @Test
    public void exitSimple() {
        doesCompileWithCorrectErrorCode("res/valid/sequence/exitSimple.wacc");
    }

    @Test
    public void whileBasic() {
        doesCompileWithCorrectErrorCode("res/valid/while/whileBasic.wacc");
    }

    @Test
    public void loopIntCondition() {
        doesCompileWithCorrectErrorCode("res/valid/while/loopIntCondition.wacc");
    }

    @Test
    public void rmStyleAddIO() {
        doesCompileWithCorrectErrorCode("res/valid/while/rmStyleAddIO.wacc");
    }

    @Test
    public void whileFalse() {
        doesCompileWithCorrectErrorCode("res/valid/while/whileFalse.wacc");
    }

    @Test
    public void fibonacciFullIt() {
        doesCompileWithCorrectErrorCode("res/valid/while/fibonacciFullIt.wacc");
    }

    @Test
    public void whileCount() {
        doesCompileWithCorrectErrorCode("res/valid/while/whileCount.wacc");
    }

    @Test
    public void min() {
        doesCompileWithCorrectErrorCode("res/valid/while/min.wacc");
    }

    @Test
    public void max() {
        doesCompileWithCorrectErrorCode("res/valid/while/max.wacc");
    }

    @Test
    public void whileBoolFlip() {
        doesCompileWithCorrectErrorCode("res/valid/while/whileBoolFlip.wacc");
    }

    @Test
    public void fibonacciIterative() {
        doesCompileWithCorrectErrorCode("res/valid/while/fibonacciIterative.wacc");
    }

    @Test
    public void loopCharCondition() {
        doesCompileWithCorrectErrorCode("res/valid/while/loopCharCondition.wacc");
    }

    @Test
    public void rmStyleAdd() {
        doesCompileWithCorrectErrorCode("res/valid/while/rmStyleAdd.wacc");
    }

}
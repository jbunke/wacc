import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

public class SyntaxErrTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    private static final int SYNTAX_ERROR_CODE = 100;

    private void doesReturnCorrectErrorCode(String filepath) {
        WACCCompiler.main(new String[]{filepath});
        exit.expectSystemExitWithStatus(SYNTAX_ERROR_CODE);
    }

    @Test
    public void arrayExpr(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/array/arrayExpr.wacc");}

    @Test
    public void badLookup01(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/pairs/badLookup01.wacc");}

    @Test
    public void badLookup02(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/pairs/badLookup02.wacc");}

    @Test
    public void ifNoelse(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/if/ifNoelse.wacc");}

    @Test
    public void ifNofi(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/if/ifNofi.wacc");}

    @Test
    public void ifNothen(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/if/ifNothen.wacc");}

    @Test
    public void ifiErr(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/if/ifiErr.wacc");}

    @Test
    public void bgnErr(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/basic/bgnErr.wacc");}

    @Test
    public void badEscape(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/basic/badEscape.wacc");}

    @Test
    public void beginNoend(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/basic/beginNoend.wacc");}

    @Test
    public void noBody(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/basic/noBody.wacc");}

    @Test
    public void badComment(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/basic/badComment.wacc");}

    @Test
    public void badComment2(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/basic/badComment2.wacc");}

    @Test
    public void multipleBegins(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/basic/multipleBegins.wacc");}

    @Test
    public void skpErr(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/basic/skpErr.wacc");}

    @Test
    public void unescapedChar(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/basic/unescapedChar.wacc");}

    @Test
    public void functionJunkAfterReturn(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/function/functionJunkAfterReturn.wacc");}

    @Test
    public void badlyNamed(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/function/badlyNamed.wacc");}

    @Test
    public void mutualRecursionNoReturn(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/function/mutualRecursionNoReturn.wacc");}

    @Test
    public void functionConditionalNoReturn(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/function/functionConditionalNoReturn.wacc");}

    @Test
    public void functionLateDefine(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/function/functionLateDefine.wacc");}

    @Test
    public void functionMissingPType(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/function/functionMissingPType.wacc");}

    @Test
    public void functionMissingCall(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/function/functionMissingCall.wacc");}

    @Test
    public void functionNoReturn(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/function/functionNoReturn.wacc");}

    @Test
    public void functionScopeDef(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/function/functionScopeDef.wacc");}

    @Test
    public void noBodyAfterFuncs(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/function/noBodyAfterFuncs.wacc");}

    @Test
    public void funcExpr2(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/function/funcExpr2.wacc");}

    @Test
    public void funcExpr(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/function/funcExpr.wacc");}

    @Test
    public void badlyPlaced(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/function/badlyPlaced.wacc");}

    @Test
    public void functionMissingParam(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/function/functionMissingParam.wacc");}

    @Test
    public void functionMissingType(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/function/functionMissingType.wacc");}

    @Test
    public void thisIsNotC(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/function/thisIsNotC.wacc");}

    @Test
    public void varNoName(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/variables/varNoName.wacc");}

    @Test
    public void badintAssignments(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/variables/badintAssignments.wacc");}

    @Test
    public void bigIntAssignment(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/variables/bigIntAssignment.wacc");}

    @Test
    public void missingOperand2(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/expressions/missingOperand2.wacc");}

    @Test
    public void printlnConcat(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/expressions/printlnConcat.wacc");}

    @Test
    public void missingOperand1(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/expressions/missingOperand1.wacc");}

    @Test
    public void missingSeq(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/sequence/missingSeq.wacc");}

    @Test
    public void emptySeq(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/sequence/emptySeq.wacc");}

    @Test
    public void endSeq(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/sequence/endSeq.wacc");}

    @Test
    public void extraSeq(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/sequence/extraSeq.wacc");}

    @Test
    public void doubleSeq(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/sequence/doubleSeq.wacc");}

    @Test
    public void whileNodo(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/while/whileNodo.wacc");}

    @Test
    public void whilErr(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/while/whilErr.wacc");}

    @Test
    public void dooErr(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/while/dooErr.wacc");}

    @Test
    public void donoErr(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/while/donoErr.wacc");}

    @Test
    public void whileNodone(){ doesReturnCorrectErrorCode("res/invalid/syntaxErr/while/whileNodone.wacc");}




}
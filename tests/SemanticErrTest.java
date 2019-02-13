import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

public class SemanticErrTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    private static final int SEMANTIC_ERROR_CODE = 200;

    private void doesReturnCorrectErrorCode(String filepath) {
        WACCCompiler.main(new String[]{filepath});
        exit.expectSystemExitWithStatus(SEMANTIC_ERROR_CODE);
    }

    @Test
    public void funcMess(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/multiple/funcMess.wacc");}

    @Test
    public void ifAndWhileErrs(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/multiple/ifAndWhileErrs.wacc");}

    @Test
    public void multiTypeErrs(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/multiple/multiTypeErrs.wacc");}

    @Test
    public void messyExpr(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/multiple/messyExpr.wacc");}

    @Test
    public void multiCaseSensitivity(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/multiple/multiCaseSensitivity.wacc");}

    @Test
    public void freeNonPair(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/pairs/freeNonPair.wacc");}

    @Test
    public void readTypeErr01(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/read/readTypeErr01.wacc");}

    @Test
    public void ifIntCondition(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/if/ifIntCondition.wacc");}

    @Test
    public void readTypeErr(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/IO/readTypeErr.wacc");}

    @Test
    public void badScopeRedefine(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/scope/badScopeRedefine.wacc");}

    @Test
    public void functionOverArgs(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/function/functionOverArgs.wacc");}

    @Test
    public void funcVarAccess(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/function/funcVarAccess.wacc");}

    @Test
    public void functionAssign(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/function/functionAssign.wacc");}

    @Test
    public void functionBadArgUse(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/function/functionBadArgUse.wacc");}

    @Test
    public void functionBadReturn(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/function/functionBadReturn.wacc");}

    @Test
    public void functionUnderArgs(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/function/functionUnderArgs.wacc");}

    @Test
    public void functionRedefine(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/function/functionRedefine.wacc");}

    @Test
    public void functionBadCall(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/function/functionBadCall.wacc");}

    @Test
    public void functionBadParam(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/function/functionBadParam.wacc");}

    @Test
    public void functionSwapArgs(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/function/functionSwapArgs.wacc");}

    @Test
    public void printTypeErr01(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/print/printTypeErr01.wacc");}

    @Test
    public void basicTypeErr12(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/variables/basicTypeErr12.wacc");}

    @Test
    public void basicTypeErr04(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/variables/basicTypeErr04.wacc");}

    @Test
    public void doubleDeclare(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/variables/doubleDeclare.wacc");}

    @Test
    public void basicTypeErr08(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/variables/basicTypeErr08.wacc");}

    @Test
    public void basicTypeErr09(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/variables/basicTypeErr09.wacc");}

    @Test
    public void basicTypeErr05(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/variables/basicTypeErr05.wacc");}

    @Test
    public void basicTypeErr02(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/variables/basicTypeErr02.wacc");}

    @Test
    public void basicTypeErr03(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/variables/basicTypeErr03.wacc");}

    @Test
    public void undeclaredScopeVar(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/variables/undeclaredScopeVar.wacc");}

    @Test
    public void undeclaredVar(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/variables/undeclaredVar.wacc");}

    @Test
    public void undeclaredVarAccess(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/variables/undeclaredVarAccess.wacc");}

    @Test
    public void basicTypeErr01(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/variables/basicTypeErr01.wacc");}

    @Test
    public void basicTypeErr06(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/variables/basicTypeErr06.wacc");}

    @Test
    public void basicTypeErr10(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/variables/basicTypeErr10.wacc");}

    @Test
    public void caseMatters(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/variables/caseMatters.wacc");}

    @Test
    public void basicTypeErr11(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/variables/basicTypeErr11.wacc");}

    @Test
    public void basicTypeErr07(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/variables/basicTypeErr07.wacc");}

    @Test
    public void mixedOpTypeErr(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/expressions/mixedOpTypeErr.wacc");}

    @Test
    public void boolOpTypeErr(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/expressions/boolOpTypeErr.wacc");}

    @Test
    public void intOpTypeErr(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/expressions/intOpTypeErr.wacc");}

    @Test
    public void exprTypeErr(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/expressions/exprTypeErr.wacc");}

    @Test
    public void lessPairExpr(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/expressions/lessPairExpr.wacc");}

    @Test
    public void moreArrExpr(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/expressions/moreArrExpr.wacc");}

    @Test
    public void badCharExit(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/exit/badCharExit.wacc");}

    @Test
    public void exitNonInt(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/exit/exitNonInt.wacc");}

    @Test
    public void globalReturn(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/exit/globalReturn.wacc");}

    @Test
    public void truErr(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/while/truErr.wacc");}

    @Test
    public void falsErr(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/while/falsErr.wacc");}

    @Test
    public void whileIntCondition(){ doesReturnCorrectErrorCode("res/invalid/semanticErr/while/whileIntCondition.wacc");}

}
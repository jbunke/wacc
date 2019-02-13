import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.util.Collections;

public class ValidFilesTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();


    private void doesCompileWithErrorCode0(String filepath){
        WACCCompiler.main(new String[]{filepath});
        exit.expectSystemExitWithStatus(0);
    }

    //Example
    @Test
    public void binarySortTree() {
        doesCompileWithErrorCode0("res/valid/advanced/binarySortTree.wacc");
    }

}
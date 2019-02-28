#!/usr/bin/env bash

runRefCompiler(){
     WACC=`sed 's:.*/::' <<< $1` >/dev/null
     NAME=`sed 's/\..*//' <<< ${WACC}` >/dev/null
     echo $2 | ./tests/refCompile -x "$1" | tee ${NAME}.out >/dev/null
     echo "Reference Output Created :" ${NAME}.out
     }

# Valid files
echo "=============== Fetching Reference Outputs ==============="
echo "..."

runRefCompiler tests/examples/valid/basic/skip/skip.wacc ''
runRefCompiler tests/examples/valid/basic/skip/comment.wacc ''
runRefCompiler tests/examples/valid/basic/skip/commentInLine.wacc ''
#runRefCompiler tests/examples/valid/basic/exit/exit-1.wacc ''
runRefCompiler tests/examples/valid/basic/exit/exitBasic.wacc ''
runRefCompiler tests/examples/valid/basic/exit/exitBasic2.wacc ''
runRefCompiler tests/examples/valid/basic/exit/exitWrap.wacc ''
#runRefCompiler tests/examples/valid/advanced/binarySortTree.wacc ''
#runRefCompiler tests/examples/valid/advanced/ticTacToe.wacc ''
#runRefCompiler tests/examples/valid/advanced/hashTable.wacc ''
#runRefCompiler tests/examples/valid/array/arrayLength.wacc ''
#runRefCompiler tests/examples/valid/array/arrayEmpty.wacc ''
#runRefCompiler tests/examples/valid/array/array.wacc ''
#runRefCompiler tests/examples/valid/array/arrayLookup.wacc ''
#runRefCompiler tests/examples/valid/array/modifyString.wacc ''
#runRefCompiler tests/examples/valid/array/printRef.wacc ''
#runRefCompiler tests/examples/valid/array/arraySimple.wacc ''
#runRefCompiler tests/examples/valid/array/arrayNested.wacc ''
#runRefCompiler tests/examples/valid/array/arrayPrint.wacc ''
#runRefCompiler tests/examples/valid/array/arrayBasic.wacc ''
#runRefCompiler tests/examples/valid/pairs/printNull.wacc ''
#runRefCompiler tests/examples/valid/pairs/printNullPair.wacc ''
#runRefCompiler tests/examples/valid/pairs/printPair.wacc ''
#runRefCompiler tests/examples/valid/pairs/nestedPair.wacc ''
#runRefCompiler tests/examples/valid/pairs/createRefPair.wacc ''
#runRefCompiler tests/examples/valid/pairs/free.wacc ''
#runRefCompiler tests/examples/valid/pairs/writeSnd.wacc ''
#runRefCompiler tests/examples/valid/pairs/writeFst.wacc ''
#runRefCompiler tests/examples/valid/pairs/printPairOfNulls.wacc ''
#runRefCompiler tests/examples/valid/pairs/null.wacc ''
#runRefCompiler tests/examples/valid/pairs/createPair02.wacc ''
#runRefCompiler tests/examples/valid/pairs/createPair.wacc ''
#runRefCompiler tests/examples/valid/pairs/checkRefPair.wacc ''
#runRefCompiler tests/examples/valid/pairs/createPair03.wacc ''
#runRefCompiler tests/examples/valid/pairs/readPair.wacc ''
#runRefCompiler tests/examples/valid/pairs/linkedList.wacc ''
#runRefCompiler tests/examples/valid/if/if1.wacc ''
#runRefCompiler tests/examples/valid/if/whitespace.wacc ''
#runRefCompiler tests/examples/valid/if/ifFalse.wacc ''
#runRefCompiler tests/examples/valid/if/ifBasic.wacc ''
#runRefCompiler tests/examples/valid/if/if6.wacc ''
#runRefCompiler tests/examples/valid/if/ifTrue.wacc ''
#runRefCompiler tests/examples/valid/if/if5.wacc ''
#runRefCompiler tests/examples/valid/if/if4.wacc ''
#runRefCompiler tests/examples/valid/if/if3.wacc ''
#runRefCompiler tests/examples/valid/if/if2.wacc ''
#runRefCompiler tests/examples/valid/runtimeErr/integerOverflow/intJustOverflow.wacc ''
#runRefCompiler tests/examples/valid/runtimeErr/integerOverflow/intnegateOverflow.wacc ''
#runRefCompiler tests/examples/valid/runtimeErr/integerOverflow/intmultOverflow.wacc ''
#runRefCompiler tests/examples/valid/runtimeErr/integerOverflow/intnegateOverflow3.wacc ''
#runRefCompiler tests/examples/valid/runtimeErr/integerOverflow/intWayOverflow.wacc ''
#runRefCompiler tests/examples/valid/runtimeErr/integerOverflow/intnegateOverflow2.wacc ''
#runRefCompiler tests/examples/valid/runtimeErr/integerOverflow/intUnderflow.wacc ''
#runRefCompiler tests/examples/valid/runtimeErr/integerOverflow/intnegateOverflow4.wacc ''
#runRefCompiler tests/examples/valid/runtimeErr/nullDereference/readNull2.wacc ''
#runRefCompiler tests/examples/valid/runtimeErr/nullDereference/useNull2.wacc ''
#runRefCompiler tests/examples/valid/runtimeErr/nullDereference/setNull1.wacc ''
#runRefCompiler tests/examples/valid/runtimeErr/nullDereference/freeNull.wacc ''
#runRefCompiler tests/examples/valid/runtimeErr/nullDereference/setNull2.wacc ''
#runRefCompiler tests/examples/valid/runtimeErr/nullDereference/useNull1.wacc ''
#runRefCompiler tests/examples/valid/runtimeErr/nullDereference/readNull1.wacc ''
#runRefCompiler tests/examples/valid/runtimeErr/doubleFrees/doubleFree.wacc ''
#runRefCompiler tests/examples/valid/runtimeErr/doubleFrees/hiddenDoubleFree.wacc ''
#runRefCompiler tests/examples/valid/runtimeErr/divideByZero/divZero.wacc ''
#runRefCompiler tests/examples/valid/runtimeErr/divideByZero/divideByZero.wacc ''
#runRefCompiler tests/examples/valid/runtimeErr/divideByZero/modByZero.wacc ''
#runRefCompiler tests/examples/valid/runtimeErr/arrayOutOfBounds/arrayOutOfBoundsWrite.wacc ''
#runRefCompiler tests/examples/valid/runtimeErr/arrayOutOfBounds/arrayNegBounds.wacc ''
#runRefCompiler tests/examples/valid/runtimeErr/arrayOutOfBounds/arrayOutOfBounds.wacc ''
#runRefCompiler tests/examples/valid/IO/IOLoop.wacc "1,Y,2,Y,3,N"
#runRefCompiler tests/examples/valid/IO/read/echoPuncChar.wacc ''
#runRefCompiler tests/examples/valid/IO/read/echoBigInt.wacc ''
#runRefCompiler tests/examples/valid/IO/read/echoInt.wacc ''
#runRefCompiler tests/examples/valid/IO/read/echoNegInt.wacc ''
#runRefCompiler tests/examples/valid/IO/read/echoChar.wacc ''
#runRefCompiler tests/examples/valid/IO/read/echoBigNegInt.wacc ''
#runRefCompiler tests/examples/valid/IO/read/read.wacc ''
#runRefCompiler tests/examples/valid/IO/IOSequence.wacc ''
#runRefCompiler tests/examples/valid/IO/print/println.wacc ''
#runRefCompiler tests/examples/valid/IO/print/stringAssignmentWithPrint.wacc ''
#runRefCompiler tests/examples/valid/IO/print/printEscChar.wacc ''
#runRefCompiler tests/examples/valid/IO/print/multipleStringsAssignment.wacc ''
#runRefCompiler tests/examples/valid/IO/print/print.wacc ''
#runRefCompiler tests/examples/valid/IO/print/hashInProgram.wacc ''
#runRefCompiler tests/examples/valid/IO/print/printBool.wacc ''
#runRefCompiler tests/examples/valid/IO/print/printInt.wacc ''
#runRefCompiler tests/examples/valid/IO/print/printChar.wacc ''
#runRefCompiler tests/examples/valid/scope/printAllTypes.wacc ''
#runRefCompiler tests/examples/valid/scope/scopeSimpleRedefine.wacc ''
#runRefCompiler tests/examples/valid/scope/scopeRedefine.wacc ''
#runRefCompiler tests/examples/valid/scope/indentationNotImportant.wacc ''
#runRefCompiler tests/examples/valid/scope/scope.wacc ''
#runRefCompiler tests/examples/valid/scope/scopeBasic.wacc ''
#runRefCompiler tests/examples/valid/scope/ifNested2.wacc ''
#runRefCompiler tests/examples/valid/scope/ifNested1.wacc ''
#runRefCompiler tests/examples/valid/scope/scopeVars.wacc ''
#runRefCompiler tests/examples/valid/scope/intsAndKeywords.wacc ''
#runRefCompiler tests/examples/valid/function/nested_functions/fixedPointRealArithmetic.wacc ''
#runRefCompiler tests/examples/valid/function/nested_functions/printTriangle.wacc ''
#runRefCompiler tests/examples/valid/function/nested_functions/printInputTriangle.wacc ''
#runRefCompiler tests/examples/valid/function/nested_functions/mutualRecursion.wacc ''
#runRefCompiler tests/examples/valid/function/nested_functions/fibonacciFullRec.wacc ''
#runRefCompiler tests/examples/valid/function/nested_functions/functionConditionalReturn.wacc ''
#runRefCompiler tests/examples/valid/function/nested_functions/simpleRecursion.wacc ''
#runRefCompiler tests/examples/valid/function/nested_functions/fibonacciRecursive.wacc ''
#runRefCompiler tests/examples/valid/function/simple_functions/functionDeclaration.wacc ''
#runRefCompiler tests/examples/valid/function/simple_functions/sameArgName2.wacc ''
#runRefCompiler tests/examples/valid/function/simple_functions/functionManyArguments.wacc ''
#runRefCompiler tests/examples/valid/function/simple_functions/asciiTable.wacc ''
#runRefCompiler tests/examples/valid/function/simple_functions/functionSimple.wacc ''
#runRefCompiler tests/examples/valid/function/simple_functions/incFunction.wacc ''
#runRefCompiler tests/examples/valid/function/simple_functions/sameArgName.wacc ''
#runRefCompiler tests/examples/valid/function/simple_functions/negFunction.wacc ''
#runRefCompiler tests/examples/valid/function/simple_functions/functionUpdateParameter.wacc ''
#runRefCompiler tests/examples/valid/function/simple_functions/functionReturnPair.wacc ''
#runRefCompiler tests/examples/valid/variables/capCharDeclaration.wacc ''
#runRefCompiler tests/examples/valid/variables/negIntDeclaration.wacc ''
#runRefCompiler tests/examples/valid/variables/charDeclaration.wacc ''
#runRefCompiler tests/examples/valid/variables/emptyStringDeclaration.wacc ''
#runRefCompiler tests/examples/valid/variables/stringDeclaration.wacc ''
#runRefCompiler tests/examples/valid/variables/longVarNames.wacc ''
#runRefCompiler tests/examples/valid/variables/boolDeclaration.wacc ''
#runRefCompiler tests/examples/valid/variables/_VarNames.wacc ''
#runRefCompiler tests/examples/valid/variables/charDeclaration2.wacc ''
#runRefCompiler tests/examples/valid/variables/manyVariables.wacc ''
#runRefCompiler tests/examples/valid/variables/intDeclaration.wacc ''
#runRefCompiler tests/examples/valid/variables/boolDeclaration2.wacc ''
#runRefCompiler tests/examples/valid/variables/zeroIntDeclaration.wacc ''
#runRefCompiler tests/examples/valid/variables/puncCharDeclaration.wacc ''
#runRefCompiler tests/examples/valid/expressions/intCalc.wacc ''
#runRefCompiler tests/examples/valid/expressions/multNoWhitespaceExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/andExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/divExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/negBothMod.wacc ''
#runRefCompiler tests/examples/valid/expressions/longSplitExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/negDivisorDiv.wacc ''
#runRefCompiler tests/examples/valid/expressions/ordAndchrExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/lessExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/lessCharExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/negDividendDiv.wacc ''
#runRefCompiler tests/examples/valid/expressions/longExpr2.wacc ''
#runRefCompiler tests/examples/valid/expressions/longSplitExpr2.wacc ''
#runRefCompiler tests/examples/valid/expressions/minusExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/intExpr1.wacc ''
#runRefCompiler tests/examples/valid/expressions/greaterEqExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/plusPlusExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/lessEqExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/multExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/longExpr3.wacc ''
#runRefCompiler tests/examples/valid/expressions/plusNoWhitespaceExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/plusMinusExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/negDividendMod.wacc ''
#runRefCompiler tests/examples/valid/expressions/longExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/boolExpr1.wacc ''
#runRefCompiler tests/examples/valid/expressions/equalsExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/negDivisorMod.wacc ''
#runRefCompiler tests/examples/valid/expressions/negBothDiv.wacc ''
#runRefCompiler tests/examples/valid/expressions/orExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/sequentialCount.wacc ''
#runRefCompiler tests/examples/valid/expressions/notequalsExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/negExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/stringEqualsExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/notExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/greaterExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/plusExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/charComparisonExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/modExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/minusPlusExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/minusMinusExpr.wacc ''
#runRefCompiler tests/examples/valid/expressions/boolCalc.wacc ''
#runRefCompiler tests/examples/valid/expressions/minusNoWhitespaceExpr.wacc ''
#runRefCompiler tests/examples/valid/sequence/stringAssignment.wacc ''
#runRefCompiler tests/examples/valid/sequence/charAssignment.wacc ''
#runRefCompiler tests/examples/valid/sequence/intLeadingZeros.wacc ''
#runRefCompiler tests/examples/valid/sequence/boolAssignment.wacc ''
#runRefCompiler tests/examples/valid/sequence/intAssignment.wacc ''
#runRefCompiler tests/examples/valid/sequence/basicSeq.wacc ''
#runRefCompiler tests/examples/valid/sequence/basicSeq2.wacc ''
#runRefCompiler tests/examples/valid/sequence/exitSimple.wacc ''
#runRefCompiler tests/examples/valid/while/whileBasic.wacc ''
#runRefCompiler tests/examples/valid/while/loopIntCondition.wacc ''
#runRefCompiler tests/examples/valid/while/rmStyleAddIO.wacc ''
#runRefCompiler tests/examples/valid/while/whileFalse.wacc ''
#runRefCompiler tests/examples/valid/while/fibonacciFullIt.wacc ''
#runRefCompiler tests/examples/valid/while/whileCount.wacc ''
#runRefCompiler tests/examples/valid/while/min.wacc ''
#runRefCompiler tests/examples/valid/while/max.wacc ''
#runRefCompiler tests/examples/valid/while/whileBoolFlip.wacc ''
#runRefCompiler tests/examples/valid/while/fibonacciIterative.wacc ''
#runRefCompiler tests/examples/valid/while/loopCharCondition.wacc ''
#runRefCompiler tests/examples/valid/while/rmStyleAdd.wacc ''

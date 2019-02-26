#!/usr/bin/env bash

runRefCompiler(){
     WACC=`sed 's:.*/::' <<< $1` >/dev/null
     NAME=`sed 's/\..*//' <<< ${WACC}` >/dev/null
     echo $2 | ./tests/refCompile -x "$1" | tee ${NAME}.out >/dev/null
     echo "Reference Output: Created " ${NAME}.out
     }

# Valid files
echo "=============== Valid files ==============="
echo "..."
runRefCompiler tests/examples/valid/basic/skip/skip.wacc ''
runRefCompiler tests/examples/valid/basic/skip/comment.wacc ''
runRefCompiler tests/examples/valid/basic/skip/commentInLine.wacc ''
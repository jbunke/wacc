#!/usr/bin/env bash
# Use this script on a single .wacc file

    make
    ./compile "$1" >/dev/null
    code=$?
    ((total=total+1))
    if [ $code -ne 0 ]; then
        echo "Error: Compiler returned $code instead of 0 for $1"
    else
    echo "Frontend Test Passed: $1"
    fi

     WACC=`sed 's:.*/::' <<< $1` >/dev/null
     NAME=`sed 's/\..*//' <<< ${WACC}` >/dev/null
     echo $2 | ./tests/refCompile -x "$1" | tee ${NAME}.out >/dev/null
     echo "Reference Output: Created " ${NAME}.out    javac BackendTest.java

    javac BackendTest.java
    java BackendTest "$1"
    code=$?
    ((total=total+1))
    if [ $code -ne 0 ]; then
        echo "Error: Output Mismatch for $1"
    else
    echo "Backend Test Passed: $1"
    fi

    rm -fr BackendTest.class $1.s $1.out



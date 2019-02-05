parser grammar BasicParser;

options {
  tokenVocab=BasicLexer;
}

unaryOper: NOT | LEN | ORD | CHR;

binaryOper: TIMES | DIVIDE | MOD | PLUS | MINUS | GREATER_THAN |
GREATER_THAN_OR_EQUAL | LESS_THAN | LESS_THAN_OR_EQUAL | EQUAL | NOT_EQUAL |
AND | OR ;

expr: unaryOper expr
| expr binaryOper expr
| INTEGER
| OPEN_PARENTHESES expr CLOSE_PARENTHESES
;

// Types
type: baseType | arrayType | pairType;

baseType: INT | BOOL | CHAR | STRING;

// Before removing left recursion: arrayType: type OPEN_BRACKET CLOSE_BRACKET;
arrayType: baseType OPEN_BRACKET CLOSE_BRACKET
| arrayType OPEN_BRACKET CLOSE_BRACKET
| pairType OPEN_BRACKET CLOSE_BRACKET;

pairElemType: baseType | arrayType | PAIR;

pairType: PAIR OPEN_BRACKET pairElemType COMMA pairElemType CLOSE_PARENTHESES;


// EOF indicates that the program must consume to the end of the input.
prog: (expr)*  EOF ;

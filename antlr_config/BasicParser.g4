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

// EOF indicates that the program must consume to the end of the input.
prog: (expr)*  EOF ;

parser grammar WACCParser;


options {
  tokenVocab=WACCLexer;
}

unaryOper: NOT | LEN | ORD | CHR;

binaryOper: TIMES | DIVIDE | MOD | PLUS | MINUS
| GREATER_THAN |GREATER_THAN_OR_EQUAL
| LESS_THAN | LESS_THAN_OR_EQUAL
| EQUAL | NOT_EQUAL
|AND | OR;

identifier: IDENTIFIER;

expr: intLiteral
| boolLiteral
| charLiteral
| stringLiteral
| pairLiter
| identifier
| arrayElem
| unaryOper expr
| expr binaryOper expr
| OPEN_PARENTHESES expr CLOSE_PARENTHESES;

// literals
intSign: PLUS | MINUS;
intLiteral: intSign? INTEGER;
boolLiteral: TRUE | FALSE;

character: CHARACTER;
charLiteral: CHAR_QUOTE character CHAR_QUOTE;
stringLiteral: STRING_LITERAL;
pairLiter: NULL;

// types
type: baseType | arrayType | pairType;

baseType: INT | BOOL | CHAR | STRING;

arrayElem: IDENTIFIER (OPEN_BRACKET expr CLOSE_BRACKET)+;

// before removing left recursion: arrayType: type OPEN_BRACKET CLOSE_BRACKET;
arrayType: baseType OPEN_BRACKET CLOSE_BRACKET
| arrayType OPEN_BRACKET CLOSE_BRACKET
| pairType OPEN_BRACKET CLOSE_BRACKET;
arrayLiteral: OPEN_BRACKET (expr (COMMA expr)*)? CLOSE_BRACKET;

pairElem: FST expr | SND expr;

pairElemType: baseType | arrayType | PAIR;

pairType: PAIR OPEN_BRACKET pairElemType COMMA pairElemType CLOSE_PARENTHESES;

// function
func: type IDENTIFIER OPEN_PARENTHESES paramList? CLOSE_PARENTHESES IS stat END;
argList: expr (COMMA expr)*;
param: type IDENTIFIER;
paramList: param (COMMA param)*;

// assign
assignLhs: IDENTIFIER | arrayElem | pairElem;
assignRhs: expr
| arrayLiteral
| NEW_PAIR OPEN_PARENTHESES expr COMMA expr CLOSE_PARENTHESES
| pairElem
| CALL IDENTIFIER OPEN_PARENTHESES argList? CLOSE_PARENTHESES;

// statement
stat: SKP
| type IDENTIFIER EQUAL assignRhs
| assignLhs EQUAL assignRhs
| READ assignLhs
| FREE expr
| RETURN expr
| EXIT expr
| PRINT expr
| PRINTLN expr
| IF expr THEN stat ELSE stat FI
| WHILE expr DO stat DONE
| BEGIN stat END
| stat SEMI_COLON stat;

// EOF indicates that the program must consume to the end of the input.
prog: BEGIN (func)* stat END EOF ;
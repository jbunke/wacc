lexer grammar WACCLexer;

SEMI_COLON: ';';

fragment UNDERSCORE: '_';
fragment LOWERCASE: [a-z];
fragment UPPERCASE: [A-Z];

//assignment
ASSIGN: '=';

// precedence categories
MULTDIVMOD: TIMES | DIVIDE | MOD;
ADDSUB: PLUS | MINUS;
COMP_LS_GR: GREATER_THAN | GREATER_THAN_OR_EQUAL
| LESS_THAN | LESS_THAN_OR_EQUAL;
COMP_EQ: EQUAL | NOT_EQUAL;

// unary ops
UNARY: NOT | LEN | ORD | CHR | MINUS;

// operators
NOT: '!';
LEN: 'len';
ORD: 'ord';
CHR: 'chr';
TIMES: '*';
DIVIDE: '/';
MOD: '%';
PLUS: '+' ;
MINUS: '-' ;
GREATER_THAN: '>';
GREATER_THAN_OR_EQUAL: '>=';
LESS_THAN: '<';
LESS_THAN_OR_EQUAL: '<=';
EQUAL: '==';
NOT_EQUAL: '!=';
AND: '&&';
OR: '||';

// statement
SKP: 'skip';
READ: 'read';
FREE: 'free';
RETURN: 'return';
EXIT: 'exit';
PRINT: 'print';
PRINTLN: 'println';
IF: 'if';
THEN: 'then';
ELSE: 'else';
FI: 'fi';
WHILE: 'while';
DO: 'do';
DONE: 'done';
BEGIN: 'begin';
END: 'end';

// function
CALL: 'call';
IS: 'is';

// type
INT: 'int';
BOOL: 'bool';
CHAR: 'char';
STRING: 'string';

PAIR: 'pair';
FST: 'fst';
SND: 'snd';
NEW_PAIR: 'newpair';

// numbers
fragment DIGIT: '0'..'9';
INT_LIT: ('+'|'-')? DIGIT+;

// identifier
fragment IDENTIFIER_INITIAL: UNDERSCORE | LOWERCASE | UPPERCASE;
fragment IDENTIFIER_MAIN: IDENTIFIER_INITIAL | DIGIT;
IDENTIFIER: IDENTIFIER_INITIAL IDENTIFIER_MAIN*;

//ignore whitespace and line comment
WS: [ \t\n]+ -> skip;
LINE_COMMENT: '#' ~[\r\n]* -> skip;

// brackets and quotes
OPEN_PARENTHESIS: '(';
CLOSE_PARENTHESIS: ')';
OPEN_BRACKET: '[';
CLOSE_BRACKET: ']';
CHAR_QUOTE: '\'';
STR_QUOTE: '"';

// literals
TRUE: 'true';
FALSE: 'false';

fragment RESTRICTED_ASCII: ~('\\' | '\'' | '"');

NULL: 'null';
STRING_LITERAL: '"' (RESTRICTED_ASCII | ESC_CHAR)* '"';
fragment CHARACTER: RESTRICTED_ASCII | ESC_CHAR;
CHAR_LIT: '\'' CHARACTER '\'';

ESC_CHAR: '\\' ('0' | 'b' | 't' | 'n' | 'f' | 'r' | '"' | '\'' | '\\');
//EOL: '\n';

COMMA: ',';





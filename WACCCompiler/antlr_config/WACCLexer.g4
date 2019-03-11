lexer grammar WACCLexer;

//ignore whitespace and line comment
WS: [ \t\n]+ -> skip;
LINE_COMMENT: '#' ~[\r\n]* -> skip;

SEMI_COLON: ';';

//assignment
ASSIGN: '=';

// operators
NOT: '!';
LEN: 'len';
ORD: 'ord';
CHR: 'chr';
TIMES: '*';
DIVIDE: '/';
MOD: '%';
PLUS: '+';
MINUS: '-';
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
INT_LIT: DIGIT+;

// brackets and quotes
OPEN_PARENTHESIS: '(';
CLOSE_PARENTHESIS: ')';
OPEN_BRACKET: '[';
CLOSE_BRACKET: ']';
CHAR_QUOTE: '\'';
STR_QUOTE: '"';

// literals
BOOL_LITER: 'true' | 'false';

fragment RESTRICTED_ASCII: ~('\\' | '\'' | '"');

NULL: 'null';
STRING_LITERAL: '"' (RESTRICTED_ASCII | ESC_CHAR)* '"';
fragment CHARACTER: RESTRICTED_ASCII | ESC_CHAR;
CHAR_LIT: '\'' CHARACTER '\'';

ESC_CHAR: '\\' ('0' | 'b' | 't' | 'n' | 'f' | 'r' | '"' | '\'' | '\\');
//EOL: '\n';

COMMA: ',';

// identifier
IDENTIFIER: [_A-Za-z] [_A-Za-z0-9]*;

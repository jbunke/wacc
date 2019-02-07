lexer grammar WACCLexer;

//operators
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

//statement
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

//brackets and quotes
OPEN_PARENTHESES: '(' ;
CLOSE_PARENTHESES: ')' ;
OPEN_BRACKET: '[';
CLOSE_BRACKET: ']';
CHAR_QUOTE: '\'';
STR_QUOTE: '"';

// For literals
TRUE: 'true';
FALSE: 'false';
ESC: '\\';
RESTRICTED_ASCII: CHAR_QUOTE ~('\\' | '\'' | '"')* CHAR_QUOTE;
NULL: 'null';

// escape charaters
ESC_ZERO: '0';
ESC_B: 'b';
ESC_T: 't';
ESC_N: 'n';
ESC_F: 'f';
ESC_R: 'r';

//function
CALL: 'call';
IS: 'is';

//numbers
DIGIT: '0'..'9' ;

// type
INT: 'int';
BOOL: 'bool';
CHAR: 'char';
STRING: 'string';

PAIR: 'pair';
FST: 'fst';
SND: 'snd';
NEW_PAIR: 'newpair';
INTEGER: DIGIT+ ;
UNDERSCORE: '_';
LOWERCASE: [a-z];
UPPERCASE: [A-Z];

//identifier
fragment IDENTIFIER_INITIAL: UNDERSCORE | LOWERCASE | UPPERCASE;
fragment IDENTIFIER_MAIN: IDENTIFIER_INITIAL | DIGIT;
IDENTIFIER: IDENTIFIER_INITIAL IDENTIFIER_MAIN*;

EOL: '\n';

COMMA: ',';
SEMI_COLON: ';';

//stuff to ignore
// TODO
// WS:(' '|'\t'|'\r'|'\n'|)+->skip;
LINE_COMMENT: '//' ~[\r\n]* -> skip;
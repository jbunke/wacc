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

//brackets
OPEN_PARENTHESES: '(' ;
CLOSE_PARENTHESES: ')' ;
OPEN_BRACKET: '[';
CLOSE_BRACKET: ']';

//function
CALL: 'call';
IS: 'is';

//numbers
fragment DIGIT: '0'..'9' ;

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

COMMA: ',';
SEMI_COLON: ';';

//stuff to ignore
WS:(' '|'\t'|'\r'|'\n')+->skip;

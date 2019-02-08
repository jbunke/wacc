lexer grammar WACCLexer;

//numbers
fragment DIGIT: '0'..'9' ;
INTEGER: DIGIT+;

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
fragment ESC: '\\';
fragment RESTRICTED_ASCII: ~('\\' | '\'' | '"');
fragment ESC_CHAR: '0'
         | 'b'
         | 't'
         | 'n'
         | 'f'
         | 'r'
         | '"'
         | '\''
         | '\\';

NULL: 'null';
STRING_LITERAL: '"' (RESTRICTED_ASCII | ESC ESC_CHAR)* '"';
CHARACTER: RESTRICTED_ASCII | ESC ESC_CHAR;

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



// type
INT: 'int';
BOOL: 'bool';
CHAR: 'char';
STRING: 'string';

PAIR: 'pair';
FST: 'fst';
SND: 'snd';
NEW_PAIR: 'newpair';

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
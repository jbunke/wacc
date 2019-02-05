lexer grammar BasicLexer;

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

//brackets
OPEN_PARENTHESES: '(' ;
CLOSE_PARENTHESES: ')' ;
OPEN_BRACKET: '[';
CLOSE_BRACKET: ']';

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

INTEGER: DIGIT+ ;

//identifier
UNDERSCORE: '_';
fragment LOWERCASE: [a-z];
fragment UPPERCASE: [A-Z];
fragment IDENTIFIER_INITIAL: UNDERSCORE | LOWERCASE | UPPERCASE;
fragment IDENTIFIER_MAIN: UNDERSCORE | LOWERCASE | UPPERCASE | DIGIT;
fragment IDENTIFIER: IDENTIFIER_INITIAL IDENTIFIER_MAIN*;
COMMA: ',';




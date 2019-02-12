parser grammar WACCParser;

options {
  tokenVocab=WACCLexer;
}

unaryOper: NOT | LEN | ORD | CHR | MINUS;

identifier: IDENTIFIER;

expr:
  intLiteral                                # IntLitExp
| boolLiteral                               # BoolLitExp
| charLiteral                               # CharLitExp
| stringLiteral                             # StringLitExp
| pairLiter                                 # PairLitExp
| identifier                                # IdentifierExp
| arrayElem                                 # ArrayElemExp
| op=unaryOper exp=expr                     # UnaryOperExp
| expr MULTDIVMOD expr                      # MultDivModExp
| expr ADDSUB expr                          # AddSubExp
| expr COMP_LS_GR expr                      # CompLsGrExp
| expr COMP_EQ expr                         # CompEqExp
| expr AND expr                             # AndExp
| expr OR expr                              # OrExp
| OPEN_PARENTHESIS expr CLOSE_PARENTHESIS   # BracketedExpr
;

// statement
stat: SKP                                   # SkipStat
| stat SEMI_COLON stat                      # StatSeq
| type IDENTIFIER ASSIGN assignRhs          # InitAssignStat
| assignLhs ASSIGN assignRhs                # AssignStat
| READ assignLhs                            # ReadStat
| FREE expr                                 # FreeStat
| RETURN expr                               # ReturnStat
| EXIT expr                                 # ExitStat
| PRINT expr                                # PrintStat
| PRINTLN expr                              # PrintlnStat
| IF expr THEN stat ELSE stat FI            # CondStat
| WHILE expr DO stat DONE                   # WhileStat
| BEGIN stat END                            # ScopeStat
;
// literals
intLiteral: INT_LIT;
boolLiteral: TRUE | FALSE;

charLiteral: CHAR_LIT;
stringLiteral: STRING_LITERAL;
pairLiter: NULL;

// types
type: baseType                                                              # BaseTp
| type OPEN_BRACKET CLOSE_BRACKET                                           # ArrayTp
| PAIR OPEN_PARENTHESIS pairElemType COMMA pairElemType CLOSE_PARENTHESIS   # PairTp
;

baseType: INT | BOOL | CHAR | STRING;

arrayElem: IDENTIFIER (OPEN_BRACKET expr CLOSE_BRACKET)+;

// before removing left recursion: arrayType: type OPEN_BRACKET CLOSE_BRACKET;
arrayLiteral: OPEN_BRACKET (expr (COMMA expr)*)? CLOSE_BRACKET;

pairElem: FST expr | SND expr;

pairElemType: baseType
| type OPEN_BRACKET CLOSE_BRACKET
| PAIR
;

// function
func: type IDENTIFIER OPEN_PARENTHESIS paramList? CLOSE_PARENTHESIS IS stat END;
argList: expr (COMMA expr)*;
param: type IDENTIFIER;
paramList: param (COMMA param)*;


// assign
assignLhs: IDENTIFIER | arrayElem | pairElem;

assignRhs: expr
| arrayLiteral
| NEW_PAIR OPEN_PARENTHESIS expr COMMA expr CLOSE_PARENTHESIS
| pairElem
| CALL IDENTIFIER OPEN_PARENTHESIS argList? CLOSE_PARENTHESIS;


// EOF indicates that the program must consume to the end of the input.
prog: BEGIN func* stat END EOF ;
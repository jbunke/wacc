parser grammar WACCParser;

options {
  tokenVocab=WACCLexer;
}

unaryOper: NOT | LEN | ORD | CHR | MINUS;

multDivMod: TIMES | DIVIDE | MOD;
addSub: PLUS | MINUS;
compLsGr: GREATER_THAN | GREATER_THAN_OR_EQUAL
| LESS_THAN | LESS_THAN_OR_EQUAL;
compEq: EQUAL | NOT_EQUAL;

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
| expr multDivMod expr                      # MultDivModExp
| expr addSub expr                          # AddSubExp
| expr compLsGr expr                        # CompLsGrExp
| expr compEq expr                          # CompEqExp
| expr AND expr                             # AndExp
| expr OR expr                              # OrExp
| OPEN_PARENTHESES expr CLOSE_PARENTHESES   # BracketedExpr
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

pairType: PAIR OPEN_PARENTHESES pairElemType COMMA pairElemType
CLOSE_PARENTHESES;

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


// EOF indicates that the program must consume to the end of the input.
prog: BEGIN func* stat END EOF ;
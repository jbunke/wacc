parser grammar WACCParser;

options {
  tokenVocab=WACCLexer;
}

expr:
intLiteral {
        try{
          Integer.parseInt(_localctx.getText());
        }
        catch(NumberFormatException e) {
          notifyErrorListeners("Integer formatting is invalid.");
        }
    }                                             # IntLitExp
| boolLiteral                                     # BoolLitExp
| charLiteral                                     # CharLitExp
| stringLiteral                                   # StringLitExp
| pairLiter                                       # PairLitExp
| ident                                           # IdentifierExp
| arrayElem                                       # ArrayElemExp
| op=(MINUS | NOT | LEN | CHR | ORD) expr         # UnaryOperExp
| expr op=(TIMES | DIVIDE | MOD) expr             # MultDivModExp
| expr op=(PLUS | MINUS) expr                     # AddSubExp
| expr op=comparison expr                         # CompLsGrExp
| expr op=(EQUAL | NOT_EQUAL) expr                # CompEqExp
| expr AND expr                                   # AndExp
| expr OR expr                                    # OrExp
| OPEN_PARENTHESIS expr CLOSE_PARENTHESIS         # BracketedExpr
;

comparison: GREATER_THAN | GREATER_THAN_OR_EQUAL | LESS_THAN | LESS_THAN_OR_EQUAL;


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
intLiteral: (PLUS|MINUS)?INT_LIT;
boolLiteral: BOOL_LITER;
ident: IDENTIFIER;

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

pairElem: FST expr        # FstElem
| SND expr                # SndElem
;

pairElemType: baseType                          # PairElemTypeBase
| type OPEN_BRACKET CLOSE_BRACKET               # PairElemTypeArray
| PAIR                                          # PairElemTypePair
;

// function
func: type IDENTIFIER OPEN_PARENTHESIS paramList? CLOSE_PARENTHESIS IS stat END;
paramList: type IDENTIFIER (COMMA type IDENTIFIER)*;


// assign
assignLhs: IDENTIFIER   # LHSIdent
| arrayElem             # LHSArrayElem
| pairElem              # LHSPairElem
;

assignRhs: expr                                                               # RHSExpr
| arrayLiteral                                                                # RHSArrayLit
| NEW_PAIR OPEN_PARENTHESIS expr COMMA expr CLOSE_PARENTHESIS                 # RHSNewPair
| pairElem                                                                    # RHSPairElem
| CALL IDENTIFIER OPEN_PARENTHESIS (expr (COMMA expr)*)? CLOSE_PARENTHESIS    # RHSFunctionCall
;


// EOF indicates that the program must consume to the end of the input.
prog: BEGIN func* stat END EOF ;

// root-level rule for interactive shell
command: expr EOF ;
// Generated from ./WACCParser.g4 by ANTLR 4.7
package antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class WACCParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, LINE_COMMENT=2, SEMI_COLON=3, ASSIGN=4, NOT=5, LEN=6, ORD=7, CHR=8, 
		TIMES=9, DIVIDE=10, MOD=11, PLUS=12, MINUS=13, GREATER_THAN=14, GREATER_THAN_OR_EQUAL=15, 
		LESS_THAN=16, LESS_THAN_OR_EQUAL=17, EQUAL=18, NOT_EQUAL=19, AND=20, OR=21, 
		SKP=22, READ=23, FREE=24, RETURN=25, EXIT=26, PRINT=27, PRINTLN=28, IF=29, 
		THEN=30, ELSE=31, FI=32, WHILE=33, DO=34, DONE=35, BEGIN=36, END=37, CALL=38, 
		IS=39, INT=40, BOOL=41, CHAR=42, STRING=43, PAIR=44, FST=45, SND=46, NEW_PAIR=47, 
		INT_LIT=48, OPEN_PARENTHESIS=49, CLOSE_PARENTHESIS=50, OPEN_BRACKET=51, 
		CLOSE_BRACKET=52, CHAR_QUOTE=53, STR_QUOTE=54, BOOL_LITER=55, NULL=56, 
		STRING_LITERAL=57, CHAR_LIT=58, ESC_CHAR=59, COMMA=60, IDENTIFIER=61;
	public static final int
		RULE_expr = 0, RULE_comparison = 1, RULE_stat = 2, RULE_intLiteral = 3, 
		RULE_boolLiteral = 4, RULE_ident = 5, RULE_charLiteral = 6, RULE_stringLiteral = 7, 
		RULE_pairLiter = 8, RULE_type = 9, RULE_baseType = 10, RULE_arrayElem = 11, 
		RULE_arrayLiteral = 12, RULE_pairElem = 13, RULE_pairElemType = 14, RULE_func = 15, 
		RULE_paramList = 16, RULE_assignLhs = 17, RULE_assignRhs = 18, RULE_prog = 19;
	public static final String[] ruleNames = {
		"expr", "comparison", "stat", "intLiteral", "boolLiteral", "ident", "charLiteral", 
		"stringLiteral", "pairLiter", "type", "baseType", "arrayElem", "arrayLiteral", 
		"pairElem", "pairElemType", "func", "paramList", "assignLhs", "assignRhs", 
		"prog"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, "';'", "'='", "'!'", "'len'", "'ord'", "'chr'", "'*'", 
		"'/'", "'%'", "'+'", "'-'", "'>'", "'>='", "'<'", "'<='", "'=='", "'!='", 
		"'&&'", "'||'", "'skip'", "'read'", "'free'", "'return'", "'exit'", "'print'", 
		"'println'", "'if'", "'then'", "'else'", "'fi'", "'while'", "'do'", "'done'", 
		"'begin'", "'end'", "'call'", "'is'", "'int'", "'bool'", "'char'", "'string'", 
		"'pair'", "'fst'", "'snd'", "'newpair'", null, "'('", "')'", "'['", "']'", 
		"'''", "'\"'", null, "'null'", null, null, null, "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "LINE_COMMENT", "SEMI_COLON", "ASSIGN", "NOT", "LEN", "ORD", 
		"CHR", "TIMES", "DIVIDE", "MOD", "PLUS", "MINUS", "GREATER_THAN", "GREATER_THAN_OR_EQUAL", 
		"LESS_THAN", "LESS_THAN_OR_EQUAL", "EQUAL", "NOT_EQUAL", "AND", "OR", 
		"SKP", "READ", "FREE", "RETURN", "EXIT", "PRINT", "PRINTLN", "IF", "THEN", 
		"ELSE", "FI", "WHILE", "DO", "DONE", "BEGIN", "END", "CALL", "IS", "INT", 
		"BOOL", "CHAR", "STRING", "PAIR", "FST", "SND", "NEW_PAIR", "INT_LIT", 
		"OPEN_PARENTHESIS", "CLOSE_PARENTHESIS", "OPEN_BRACKET", "CLOSE_BRACKET", 
		"CHAR_QUOTE", "STR_QUOTE", "BOOL_LITER", "NULL", "STRING_LITERAL", "CHAR_LIT", 
		"ESC_CHAR", "COMMA", "IDENTIFIER"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "WACCParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public WACCParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IdentifierExpContext extends ExprContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public IdentifierExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitIdentifierExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndExpContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode AND() { return getToken(WACCParser.AND, 0); }
		public AndExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitAndExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringLitExpContext extends ExprContext {
		public StringLiteralContext stringLiteral() {
			return getRuleContext(StringLiteralContext.class,0);
		}
		public StringLitExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitStringLitExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultDivModExpContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode TIMES() { return getToken(WACCParser.TIMES, 0); }
		public TerminalNode DIVIDE() { return getToken(WACCParser.DIVIDE, 0); }
		public TerminalNode MOD() { return getToken(WACCParser.MOD, 0); }
		public MultDivModExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitMultDivModExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayElemExpContext extends ExprContext {
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public ArrayElemExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitArrayElemExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryOperExpContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(WACCParser.MINUS, 0); }
		public TerminalNode NOT() { return getToken(WACCParser.NOT, 0); }
		public TerminalNode LEN() { return getToken(WACCParser.LEN, 0); }
		public TerminalNode CHR() { return getToken(WACCParser.CHR, 0); }
		public TerminalNode ORD() { return getToken(WACCParser.ORD, 0); }
		public UnaryOperExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitUnaryOperExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BracketedExprContext extends ExprContext {
		public TerminalNode OPEN_PARENTHESIS() { return getToken(WACCParser.OPEN_PARENTHESIS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(WACCParser.CLOSE_PARENTHESIS, 0); }
		public BracketedExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitBracketedExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntLitExpContext extends ExprContext {
		public IntLiteralContext intLiteral() {
			return getRuleContext(IntLiteralContext.class,0);
		}
		public IntLitExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitIntLitExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PairLitExpContext extends ExprContext {
		public PairLiterContext pairLiter() {
			return getRuleContext(PairLiterContext.class,0);
		}
		public PairLitExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitPairLitExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CompEqExpContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode EQUAL() { return getToken(WACCParser.EQUAL, 0); }
		public TerminalNode NOT_EQUAL() { return getToken(WACCParser.NOT_EQUAL, 0); }
		public CompEqExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitCompEqExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CompLsGrExpContext extends ExprContext {
		public ComparisonContext op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public CompLsGrExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitCompLsGrExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrExpContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OR() { return getToken(WACCParser.OR, 0); }
		public OrExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitOrExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CharLitExpContext extends ExprContext {
		public CharLiteralContext charLiteral() {
			return getRuleContext(CharLiteralContext.class,0);
		}
		public CharLitExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitCharLitExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddSubExpContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(WACCParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(WACCParser.MINUS, 0); }
		public AddSubExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitAddSubExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolLitExpContext extends ExprContext {
		public BoolLiteralContext boolLiteral() {
			return getRuleContext(BoolLiteralContext.class,0);
		}
		public BoolLitExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitBoolLitExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				_localctx = new IntLitExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(41);
				intLiteral();

				        try{
				          Integer.parseInt(_localctx.getText());
				        }
				        catch(NumberFormatException e) {
				          notifyErrorListeners("Integer formatting is invalid.");
				        }
				    
				}
				break;
			case 2:
				{
				_localctx = new BoolLitExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(44);
				boolLiteral();
				}
				break;
			case 3:
				{
				_localctx = new CharLitExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(45);
				charLiteral();
				}
				break;
			case 4:
				{
				_localctx = new StringLitExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(46);
				stringLiteral();
				}
				break;
			case 5:
				{
				_localctx = new PairLitExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(47);
				pairLiter();
				}
				break;
			case 6:
				{
				_localctx = new IdentifierExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(48);
				ident();
				}
				break;
			case 7:
				{
				_localctx = new ArrayElemExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(49);
				arrayElem();
				}
				break;
			case 8:
				{
				_localctx = new UnaryOperExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(50);
				((UnaryOperExpContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << MINUS))) != 0)) ) {
					((UnaryOperExpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(51);
				expr(8);
				}
				break;
			case 9:
				{
				_localctx = new BracketedExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(52);
				match(OPEN_PARENTHESIS);
				setState(53);
				expr(0);
				setState(54);
				match(CLOSE_PARENTHESIS);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(79);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(77);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new MultDivModExpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(58);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(59);
						((MultDivModExpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TIMES) | (1L << DIVIDE) | (1L << MOD))) != 0)) ) {
							((MultDivModExpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(60);
						expr(8);
						}
						break;
					case 2:
						{
						_localctx = new AddSubExpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(61);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(62);
						((AddSubExpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((AddSubExpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(63);
						expr(7);
						}
						break;
					case 3:
						{
						_localctx = new CompLsGrExpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(64);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(65);
						((CompLsGrExpContext)_localctx).op = comparison();
						setState(66);
						expr(6);
						}
						break;
					case 4:
						{
						_localctx = new CompEqExpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(68);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(69);
						((CompEqExpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQUAL || _la==NOT_EQUAL) ) {
							((CompEqExpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(70);
						expr(5);
						}
						break;
					case 5:
						{
						_localctx = new AndExpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(71);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(72);
						match(AND);
						setState(73);
						expr(4);
						}
						break;
					case 6:
						{
						_localctx = new OrExpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(74);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(75);
						match(OR);
						setState(76);
						expr(3);
						}
						break;
					}
					} 
				}
				setState(81);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ComparisonContext extends ParserRuleContext {
		public TerminalNode GREATER_THAN() { return getToken(WACCParser.GREATER_THAN, 0); }
		public TerminalNode GREATER_THAN_OR_EQUAL() { return getToken(WACCParser.GREATER_THAN_OR_EQUAL, 0); }
		public TerminalNode LESS_THAN() { return getToken(WACCParser.LESS_THAN, 0); }
		public TerminalNode LESS_THAN_OR_EQUAL() { return getToken(WACCParser.LESS_THAN_OR_EQUAL, 0); }
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitComparison(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_comparison);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER_THAN) | (1L << GREATER_THAN_OR_EQUAL) | (1L << LESS_THAN) | (1L << LESS_THAN_OR_EQUAL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	 
		public StatContext() { }
		public void copyFrom(StatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ReturnStatContext extends StatContext {
		public TerminalNode RETURN() { return getToken(WACCParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReturnStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitReturnStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExitStatContext extends StatContext {
		public TerminalNode EXIT() { return getToken(WACCParser.EXIT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExitStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitExitStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ScopeStatContext extends StatContext {
		public TerminalNode BEGIN() { return getToken(WACCParser.BEGIN, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode END() { return getToken(WACCParser.END, 0); }
		public ScopeStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitScopeStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FreeStatContext extends StatContext {
		public TerminalNode FREE() { return getToken(WACCParser.FREE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FreeStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitFreeStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SkipStatContext extends StatContext {
		public TerminalNode SKP() { return getToken(WACCParser.SKP, 0); }
		public SkipStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitSkipStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InitAssignStatContext extends StatContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(WACCParser.IDENTIFIER, 0); }
		public TerminalNode ASSIGN() { return getToken(WACCParser.ASSIGN, 0); }
		public AssignRhsContext assignRhs() {
			return getRuleContext(AssignRhsContext.class,0);
		}
		public InitAssignStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitInitAssignStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileStatContext extends StatContext {
		public TerminalNode WHILE() { return getToken(WACCParser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DO() { return getToken(WACCParser.DO, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode DONE() { return getToken(WACCParser.DONE, 0); }
		public WhileStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitWhileStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReadStatContext extends StatContext {
		public TerminalNode READ() { return getToken(WACCParser.READ, 0); }
		public AssignLhsContext assignLhs() {
			return getRuleContext(AssignLhsContext.class,0);
		}
		public ReadStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitReadStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatSeqContext extends StatContext {
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TerminalNode SEMI_COLON() { return getToken(WACCParser.SEMI_COLON, 0); }
		public StatSeqContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitStatSeq(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrintlnStatContext extends StatContext {
		public TerminalNode PRINTLN() { return getToken(WACCParser.PRINTLN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrintlnStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitPrintlnStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignStatContext extends StatContext {
		public AssignLhsContext assignLhs() {
			return getRuleContext(AssignLhsContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(WACCParser.ASSIGN, 0); }
		public AssignRhsContext assignRhs() {
			return getRuleContext(AssignRhsContext.class,0);
		}
		public AssignStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitAssignStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CondStatContext extends StatContext {
		public TerminalNode IF() { return getToken(WACCParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode THEN() { return getToken(WACCParser.THEN, 0); }
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(WACCParser.ELSE, 0); }
		public TerminalNode FI() { return getToken(WACCParser.FI, 0); }
		public CondStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitCondStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrintStatContext extends StatContext {
		public TerminalNode PRINT() { return getToken(WACCParser.PRINT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrintStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitPrintStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		return stat(0);
	}

	private StatContext stat(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StatContext _localctx = new StatContext(_ctx, _parentState);
		StatContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_stat, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SKP:
				{
				_localctx = new SkipStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(85);
				match(SKP);
				}
				break;
			case INT:
			case BOOL:
			case CHAR:
			case STRING:
			case PAIR:
				{
				_localctx = new InitAssignStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(86);
				type(0);
				setState(87);
				match(IDENTIFIER);
				setState(88);
				match(ASSIGN);
				setState(89);
				assignRhs();
				}
				break;
			case FST:
			case SND:
			case IDENTIFIER:
				{
				_localctx = new AssignStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(91);
				assignLhs();
				setState(92);
				match(ASSIGN);
				setState(93);
				assignRhs();
				}
				break;
			case READ:
				{
				_localctx = new ReadStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(95);
				match(READ);
				setState(96);
				assignLhs();
				}
				break;
			case FREE:
				{
				_localctx = new FreeStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(97);
				match(FREE);
				setState(98);
				expr(0);
				}
				break;
			case RETURN:
				{
				_localctx = new ReturnStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(99);
				match(RETURN);
				setState(100);
				expr(0);
				}
				break;
			case EXIT:
				{
				_localctx = new ExitStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(101);
				match(EXIT);
				setState(102);
				expr(0);
				}
				break;
			case PRINT:
				{
				_localctx = new PrintStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(103);
				match(PRINT);
				setState(104);
				expr(0);
				}
				break;
			case PRINTLN:
				{
				_localctx = new PrintlnStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(105);
				match(PRINTLN);
				setState(106);
				expr(0);
				}
				break;
			case IF:
				{
				_localctx = new CondStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(107);
				match(IF);
				setState(108);
				expr(0);
				setState(109);
				match(THEN);
				setState(110);
				stat(0);
				setState(111);
				match(ELSE);
				setState(112);
				stat(0);
				setState(113);
				match(FI);
				}
				break;
			case WHILE:
				{
				_localctx = new WhileStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(115);
				match(WHILE);
				setState(116);
				expr(0);
				setState(117);
				match(DO);
				setState(118);
				stat(0);
				setState(119);
				match(DONE);
				}
				break;
			case BEGIN:
				{
				_localctx = new ScopeStatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(121);
				match(BEGIN);
				setState(122);
				stat(0);
				setState(123);
				match(END);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(132);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StatSeqContext(new StatContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_stat);
					setState(127);
					if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
					setState(128);
					match(SEMI_COLON);
					setState(129);
					stat(13);
					}
					} 
				}
				setState(134);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class IntLiteralContext extends ParserRuleContext {
		public TerminalNode INT_LIT() { return getToken(WACCParser.INT_LIT, 0); }
		public TerminalNode PLUS() { return getToken(WACCParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(WACCParser.MINUS, 0); }
		public IntLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intLiteral; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitIntLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntLiteralContext intLiteral() throws RecognitionException {
		IntLiteralContext _localctx = new IntLiteralContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_intLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PLUS || _la==MINUS) {
				{
				setState(135);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(138);
			match(INT_LIT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolLiteralContext extends ParserRuleContext {
		public TerminalNode BOOL_LITER() { return getToken(WACCParser.BOOL_LITER, 0); }
		public BoolLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolLiteral; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitBoolLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolLiteralContext boolLiteral() throws RecognitionException {
		BoolLiteralContext _localctx = new BoolLiteralContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_boolLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			match(BOOL_LITER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(WACCParser.IDENTIFIER, 0); }
		public IdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitIdent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentContext ident() throws RecognitionException {
		IdentContext _localctx = new IdentContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_ident);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CharLiteralContext extends ParserRuleContext {
		public TerminalNode CHAR_LIT() { return getToken(WACCParser.CHAR_LIT, 0); }
		public CharLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charLiteral; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitCharLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharLiteralContext charLiteral() throws RecognitionException {
		CharLiteralContext _localctx = new CharLiteralContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_charLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(CHAR_LIT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringLiteralContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(WACCParser.STRING_LITERAL, 0); }
		public StringLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringLiteral; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitStringLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringLiteralContext stringLiteral() throws RecognitionException {
		StringLiteralContext _localctx = new StringLiteralContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_stringLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(STRING_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairLiterContext extends ParserRuleContext {
		public TerminalNode NULL() { return getToken(WACCParser.NULL, 0); }
		public PairLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitPairLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairLiterContext pairLiter() throws RecognitionException {
		PairLiterContext _localctx = new PairLiterContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_pairLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(NULL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BaseTpContext extends TypeContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public BaseTpContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitBaseTp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayTpContext extends TypeContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(WACCParser.OPEN_BRACKET, 0); }
		public TerminalNode CLOSE_BRACKET() { return getToken(WACCParser.CLOSE_BRACKET, 0); }
		public ArrayTpContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitArrayTp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PairTpContext extends TypeContext {
		public TerminalNode PAIR() { return getToken(WACCParser.PAIR, 0); }
		public TerminalNode OPEN_PARENTHESIS() { return getToken(WACCParser.OPEN_PARENTHESIS, 0); }
		public List<PairElemTypeContext> pairElemType() {
			return getRuleContexts(PairElemTypeContext.class);
		}
		public PairElemTypeContext pairElemType(int i) {
			return getRuleContext(PairElemTypeContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(WACCParser.COMMA, 0); }
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(WACCParser.CLOSE_PARENTHESIS, 0); }
		public PairTpContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitPairTp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case CHAR:
			case STRING:
				{
				_localctx = new BaseTpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(151);
				baseType();
				}
				break;
			case PAIR:
				{
				_localctx = new PairTpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(152);
				match(PAIR);
				setState(153);
				match(OPEN_PARENTHESIS);
				setState(154);
				pairElemType();
				setState(155);
				match(COMMA);
				setState(156);
				pairElemType();
				setState(157);
				match(CLOSE_PARENTHESIS);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(166);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ArrayTpContext(new TypeContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(161);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(162);
					match(OPEN_BRACKET);
					setState(163);
					match(CLOSE_BRACKET);
					}
					} 
				}
				setState(168);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BaseTypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(WACCParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(WACCParser.BOOL, 0); }
		public TerminalNode CHAR() { return getToken(WACCParser.CHAR, 0); }
		public TerminalNode STRING() { return getToken(WACCParser.STRING, 0); }
		public BaseTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitBaseType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseTypeContext baseType() throws RecognitionException {
		BaseTypeContext _localctx = new BaseTypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_baseType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayElemContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(WACCParser.IDENTIFIER, 0); }
		public List<TerminalNode> OPEN_BRACKET() { return getTokens(WACCParser.OPEN_BRACKET); }
		public TerminalNode OPEN_BRACKET(int i) {
			return getToken(WACCParser.OPEN_BRACKET, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> CLOSE_BRACKET() { return getTokens(WACCParser.CLOSE_BRACKET); }
		public TerminalNode CLOSE_BRACKET(int i) {
			return getToken(WACCParser.CLOSE_BRACKET, i);
		}
		public ArrayElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayElem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitArrayElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayElemContext arrayElem() throws RecognitionException {
		ArrayElemContext _localctx = new ArrayElemContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_arrayElem);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			match(IDENTIFIER);
			setState(176); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(172);
					match(OPEN_BRACKET);
					setState(173);
					expr(0);
					setState(174);
					match(CLOSE_BRACKET);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(178); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayLiteralContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACKET() { return getToken(WACCParser.OPEN_BRACKET, 0); }
		public TerminalNode CLOSE_BRACKET() { return getToken(WACCParser.CLOSE_BRACKET, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(WACCParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(WACCParser.COMMA, i);
		}
		public ArrayLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLiteral; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitArrayLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayLiteralContext arrayLiteral() throws RecognitionException {
		ArrayLiteralContext _localctx = new ArrayLiteralContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_arrayLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(OPEN_BRACKET);
			setState(189);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << PLUS) | (1L << MINUS) | (1L << INT_LIT) | (1L << OPEN_PARENTHESIS) | (1L << BOOL_LITER) | (1L << NULL) | (1L << STRING_LITERAL) | (1L << CHAR_LIT) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(181);
				expr(0);
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(182);
					match(COMMA);
					setState(183);
					expr(0);
					}
					}
					setState(188);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(191);
			match(CLOSE_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairElemContext extends ParserRuleContext {
		public PairElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairElem; }
	 
		public PairElemContext() { }
		public void copyFrom(PairElemContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FstElemContext extends PairElemContext {
		public TerminalNode FST() { return getToken(WACCParser.FST, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FstElemContext(PairElemContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitFstElem(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SndElemContext extends PairElemContext {
		public TerminalNode SND() { return getToken(WACCParser.SND, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public SndElemContext(PairElemContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitSndElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairElemContext pairElem() throws RecognitionException {
		PairElemContext _localctx = new PairElemContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_pairElem);
		try {
			setState(197);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FST:
				_localctx = new FstElemContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(193);
				match(FST);
				setState(194);
				expr(0);
				}
				break;
			case SND:
				_localctx = new SndElemContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(195);
				match(SND);
				setState(196);
				expr(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairElemTypeContext extends ParserRuleContext {
		public PairElemTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairElemType; }
	 
		public PairElemTypeContext() { }
		public void copyFrom(PairElemTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PairElemTypeBaseContext extends PairElemTypeContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public PairElemTypeBaseContext(PairElemTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitPairElemTypeBase(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PairElemTypePairContext extends PairElemTypeContext {
		public TerminalNode PAIR() { return getToken(WACCParser.PAIR, 0); }
		public PairElemTypePairContext(PairElemTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitPairElemTypePair(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PairElemTypeArrayContext extends PairElemTypeContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(WACCParser.OPEN_BRACKET, 0); }
		public TerminalNode CLOSE_BRACKET() { return getToken(WACCParser.CLOSE_BRACKET, 0); }
		public PairElemTypeArrayContext(PairElemTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitPairElemTypeArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairElemTypeContext pairElemType() throws RecognitionException {
		PairElemTypeContext _localctx = new PairElemTypeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_pairElemType);
		try {
			setState(205);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new PairElemTypeBaseContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(199);
				baseType();
				}
				break;
			case 2:
				_localctx = new PairElemTypeArrayContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(200);
				type(0);
				setState(201);
				match(OPEN_BRACKET);
				setState(202);
				match(CLOSE_BRACKET);
				}
				break;
			case 3:
				_localctx = new PairElemTypePairContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(204);
				match(PAIR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(WACCParser.IDENTIFIER, 0); }
		public TerminalNode OPEN_PARENTHESIS() { return getToken(WACCParser.OPEN_PARENTHESIS, 0); }
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(WACCParser.CLOSE_PARENTHESIS, 0); }
		public TerminalNode IS() { return getToken(WACCParser.IS, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode END() { return getToken(WACCParser.END, 0); }
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public FuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_func);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			type(0);
			setState(208);
			match(IDENTIFIER);
			setState(209);
			match(OPEN_PARENTHESIS);
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING) | (1L << PAIR))) != 0)) {
				{
				setState(210);
				paramList();
				}
			}

			setState(213);
			match(CLOSE_PARENTHESIS);
			setState(214);
			match(IS);
			setState(215);
			stat(0);
			setState(216);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamListContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(WACCParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(WACCParser.IDENTIFIER, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(WACCParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(WACCParser.COMMA, i);
		}
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			type(0);
			setState(219);
			match(IDENTIFIER);
			setState(226);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(220);
				match(COMMA);
				setState(221);
				type(0);
				setState(222);
				match(IDENTIFIER);
				}
				}
				setState(228);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignLhsContext extends ParserRuleContext {
		public AssignLhsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignLhs; }
	 
		public AssignLhsContext() { }
		public void copyFrom(AssignLhsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LHSIdentContext extends AssignLhsContext {
		public TerminalNode IDENTIFIER() { return getToken(WACCParser.IDENTIFIER, 0); }
		public LHSIdentContext(AssignLhsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitLHSIdent(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LHSArrayElemContext extends AssignLhsContext {
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public LHSArrayElemContext(AssignLhsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitLHSArrayElem(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LHSPairElemContext extends AssignLhsContext {
		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class,0);
		}
		public LHSPairElemContext(AssignLhsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitLHSPairElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignLhsContext assignLhs() throws RecognitionException {
		AssignLhsContext _localctx = new AssignLhsContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_assignLhs);
		try {
			setState(232);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				_localctx = new LHSIdentContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(229);
				match(IDENTIFIER);
				}
				break;
			case 2:
				_localctx = new LHSArrayElemContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(230);
				arrayElem();
				}
				break;
			case 3:
				_localctx = new LHSPairElemContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(231);
				pairElem();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignRhsContext extends ParserRuleContext {
		public AssignRhsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignRhs; }
	 
		public AssignRhsContext() { }
		public void copyFrom(AssignRhsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class RHSPairElemContext extends AssignRhsContext {
		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class,0);
		}
		public RHSPairElemContext(AssignRhsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitRHSPairElem(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RHSFunctionCallContext extends AssignRhsContext {
		public TerminalNode CALL() { return getToken(WACCParser.CALL, 0); }
		public TerminalNode IDENTIFIER() { return getToken(WACCParser.IDENTIFIER, 0); }
		public TerminalNode OPEN_PARENTHESIS() { return getToken(WACCParser.OPEN_PARENTHESIS, 0); }
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(WACCParser.CLOSE_PARENTHESIS, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(WACCParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(WACCParser.COMMA, i);
		}
		public RHSFunctionCallContext(AssignRhsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitRHSFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RHSNewPairContext extends AssignRhsContext {
		public TerminalNode NEW_PAIR() { return getToken(WACCParser.NEW_PAIR, 0); }
		public TerminalNode OPEN_PARENTHESIS() { return getToken(WACCParser.OPEN_PARENTHESIS, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(WACCParser.COMMA, 0); }
		public TerminalNode CLOSE_PARENTHESIS() { return getToken(WACCParser.CLOSE_PARENTHESIS, 0); }
		public RHSNewPairContext(AssignRhsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitRHSNewPair(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RHSArrayLitContext extends AssignRhsContext {
		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class,0);
		}
		public RHSArrayLitContext(AssignRhsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitRHSArrayLit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RHSExprContext extends AssignRhsContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public RHSExprContext(AssignRhsContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitRHSExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignRhsContext assignRhs() throws RecognitionException {
		AssignRhsContext _localctx = new AssignRhsContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_assignRhs);
		int _la;
		try {
			setState(258);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
			case LEN:
			case ORD:
			case CHR:
			case PLUS:
			case MINUS:
			case INT_LIT:
			case OPEN_PARENTHESIS:
			case BOOL_LITER:
			case NULL:
			case STRING_LITERAL:
			case CHAR_LIT:
			case IDENTIFIER:
				_localctx = new RHSExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(234);
				expr(0);
				}
				break;
			case OPEN_BRACKET:
				_localctx = new RHSArrayLitContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(235);
				arrayLiteral();
				}
				break;
			case NEW_PAIR:
				_localctx = new RHSNewPairContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(236);
				match(NEW_PAIR);
				setState(237);
				match(OPEN_PARENTHESIS);
				setState(238);
				expr(0);
				setState(239);
				match(COMMA);
				setState(240);
				expr(0);
				setState(241);
				match(CLOSE_PARENTHESIS);
				}
				break;
			case FST:
			case SND:
				_localctx = new RHSPairElemContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(243);
				pairElem();
				}
				break;
			case CALL:
				_localctx = new RHSFunctionCallContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(244);
				match(CALL);
				setState(245);
				match(IDENTIFIER);
				setState(246);
				match(OPEN_PARENTHESIS);
				setState(255);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << PLUS) | (1L << MINUS) | (1L << INT_LIT) | (1L << OPEN_PARENTHESIS) | (1L << BOOL_LITER) | (1L << NULL) | (1L << STRING_LITERAL) | (1L << CHAR_LIT) | (1L << IDENTIFIER))) != 0)) {
					{
					setState(247);
					expr(0);
					setState(252);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(248);
						match(COMMA);
						setState(249);
						expr(0);
						}
						}
						setState(254);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(257);
				match(CLOSE_PARENTHESIS);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProgContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(WACCParser.BEGIN, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode END() { return getToken(WACCParser.END, 0); }
		public TerminalNode EOF() { return getToken(WACCParser.EOF, 0); }
		public List<FuncContext> func() {
			return getRuleContexts(FuncContext.class);
		}
		public FuncContext func(int i) {
			return getRuleContext(FuncContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WACCParserVisitor ) return ((WACCParserVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_prog);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			match(BEGIN);
			setState(264);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(261);
					func();
					}
					} 
				}
				setState(266);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			setState(267);
			stat(0);
			setState(268);
			match(END);
			setState(269);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return expr_sempred((ExprContext)_localctx, predIndex);
		case 2:
			return stat_sempred((StatContext)_localctx, predIndex);
		case 9:
			return type_sempred((TypeContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean stat_sempred(StatContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 12);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3?\u0112\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\5\2;\n\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2P\n\2\f\2\16\2S\13\2\3\3\3"+
		"\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u0080\n\4\3\4\3\4\3\4\7\4\u0085\n\4\f\4"+
		"\16\4\u0088\13\4\3\5\5\5\u008b\n\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t"+
		"\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00a2\n"+
		"\13\3\13\3\13\3\13\7\13\u00a7\n\13\f\13\16\13\u00aa\13\13\3\f\3\f\3\r"+
		"\3\r\3\r\3\r\3\r\6\r\u00b3\n\r\r\r\16\r\u00b4\3\16\3\16\3\16\3\16\7\16"+
		"\u00bb\n\16\f\16\16\16\u00be\13\16\5\16\u00c0\n\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\5\17\u00c8\n\17\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00d0\n"+
		"\20\3\21\3\21\3\21\3\21\5\21\u00d6\n\21\3\21\3\21\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\7\22\u00e3\n\22\f\22\16\22\u00e6\13\22\3\23"+
		"\3\23\3\23\5\23\u00eb\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\7\24\u00fd\n\24\f\24\16\24\u0100\13"+
		"\24\5\24\u0102\n\24\3\24\5\24\u0105\n\24\3\25\3\25\7\25\u0109\n\25\f\25"+
		"\16\25\u010c\13\25\3\25\3\25\3\25\3\25\3\25\2\5\2\6\24\26\2\4\6\b\n\f"+
		"\16\20\22\24\26\30\32\34\36 \"$&(\2\b\4\2\7\n\17\17\3\2\13\r\3\2\16\17"+
		"\3\2\24\25\3\2\20\23\3\2*-\2\u012b\2:\3\2\2\2\4T\3\2\2\2\6\177\3\2\2\2"+
		"\b\u008a\3\2\2\2\n\u008e\3\2\2\2\f\u0090\3\2\2\2\16\u0092\3\2\2\2\20\u0094"+
		"\3\2\2\2\22\u0096\3\2\2\2\24\u00a1\3\2\2\2\26\u00ab\3\2\2\2\30\u00ad\3"+
		"\2\2\2\32\u00b6\3\2\2\2\34\u00c7\3\2\2\2\36\u00cf\3\2\2\2 \u00d1\3\2\2"+
		"\2\"\u00dc\3\2\2\2$\u00ea\3\2\2\2&\u0104\3\2\2\2(\u0106\3\2\2\2*+\b\2"+
		"\1\2+,\5\b\5\2,-\b\2\1\2-;\3\2\2\2.;\5\n\6\2/;\5\16\b\2\60;\5\20\t\2\61"+
		";\5\22\n\2\62;\5\f\7\2\63;\5\30\r\2\64\65\t\2\2\2\65;\5\2\2\n\66\67\7"+
		"\63\2\2\678\5\2\2\289\7\64\2\29;\3\2\2\2:*\3\2\2\2:.\3\2\2\2:/\3\2\2\2"+
		":\60\3\2\2\2:\61\3\2\2\2:\62\3\2\2\2:\63\3\2\2\2:\64\3\2\2\2:\66\3\2\2"+
		"\2;Q\3\2\2\2<=\f\t\2\2=>\t\3\2\2>P\5\2\2\n?@\f\b\2\2@A\t\4\2\2AP\5\2\2"+
		"\tBC\f\7\2\2CD\5\4\3\2DE\5\2\2\bEP\3\2\2\2FG\f\6\2\2GH\t\5\2\2HP\5\2\2"+
		"\7IJ\f\5\2\2JK\7\26\2\2KP\5\2\2\6LM\f\4\2\2MN\7\27\2\2NP\5\2\2\5O<\3\2"+
		"\2\2O?\3\2\2\2OB\3\2\2\2OF\3\2\2\2OI\3\2\2\2OL\3\2\2\2PS\3\2\2\2QO\3\2"+
		"\2\2QR\3\2\2\2R\3\3\2\2\2SQ\3\2\2\2TU\t\6\2\2U\5\3\2\2\2VW\b\4\1\2W\u0080"+
		"\7\30\2\2XY\5\24\13\2YZ\7?\2\2Z[\7\6\2\2[\\\5&\24\2\\\u0080\3\2\2\2]^"+
		"\5$\23\2^_\7\6\2\2_`\5&\24\2`\u0080\3\2\2\2ab\7\31\2\2b\u0080\5$\23\2"+
		"cd\7\32\2\2d\u0080\5\2\2\2ef\7\33\2\2f\u0080\5\2\2\2gh\7\34\2\2h\u0080"+
		"\5\2\2\2ij\7\35\2\2j\u0080\5\2\2\2kl\7\36\2\2l\u0080\5\2\2\2mn\7\37\2"+
		"\2no\5\2\2\2op\7 \2\2pq\5\6\4\2qr\7!\2\2rs\5\6\4\2st\7\"\2\2t\u0080\3"+
		"\2\2\2uv\7#\2\2vw\5\2\2\2wx\7$\2\2xy\5\6\4\2yz\7%\2\2z\u0080\3\2\2\2{"+
		"|\7&\2\2|}\5\6\4\2}~\7\'\2\2~\u0080\3\2\2\2\177V\3\2\2\2\177X\3\2\2\2"+
		"\177]\3\2\2\2\177a\3\2\2\2\177c\3\2\2\2\177e\3\2\2\2\177g\3\2\2\2\177"+
		"i\3\2\2\2\177k\3\2\2\2\177m\3\2\2\2\177u\3\2\2\2\177{\3\2\2\2\u0080\u0086"+
		"\3\2\2\2\u0081\u0082\f\16\2\2\u0082\u0083\7\5\2\2\u0083\u0085\5\6\4\17"+
		"\u0084\u0081\3\2\2\2\u0085\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087"+
		"\3\2\2\2\u0087\7\3\2\2\2\u0088\u0086\3\2\2\2\u0089\u008b\t\4\2\2\u008a"+
		"\u0089\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\7\62"+
		"\2\2\u008d\t\3\2\2\2\u008e\u008f\79\2\2\u008f\13\3\2\2\2\u0090\u0091\7"+
		"?\2\2\u0091\r\3\2\2\2\u0092\u0093\7<\2\2\u0093\17\3\2\2\2\u0094\u0095"+
		"\7;\2\2\u0095\21\3\2\2\2\u0096\u0097\7:\2\2\u0097\23\3\2\2\2\u0098\u0099"+
		"\b\13\1\2\u0099\u00a2\5\26\f\2\u009a\u009b\7.\2\2\u009b\u009c\7\63\2\2"+
		"\u009c\u009d\5\36\20\2\u009d\u009e\7>\2\2\u009e\u009f\5\36\20\2\u009f"+
		"\u00a0\7\64\2\2\u00a0\u00a2\3\2\2\2\u00a1\u0098\3\2\2\2\u00a1\u009a\3"+
		"\2\2\2\u00a2\u00a8\3\2\2\2\u00a3\u00a4\f\4\2\2\u00a4\u00a5\7\65\2\2\u00a5"+
		"\u00a7\7\66\2\2\u00a6\u00a3\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3"+
		"\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\25\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab"+
		"\u00ac\t\7\2\2\u00ac\27\3\2\2\2\u00ad\u00b2\7?\2\2\u00ae\u00af\7\65\2"+
		"\2\u00af\u00b0\5\2\2\2\u00b0\u00b1\7\66\2\2\u00b1\u00b3\3\2\2\2\u00b2"+
		"\u00ae\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2"+
		"\2\2\u00b5\31\3\2\2\2\u00b6\u00bf\7\65\2\2\u00b7\u00bc\5\2\2\2\u00b8\u00b9"+
		"\7>\2\2\u00b9\u00bb\5\2\2\2\u00ba\u00b8\3\2\2\2\u00bb\u00be\3\2\2\2\u00bc"+
		"\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2"+
		"\2\2\u00bf\u00b7\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1"+
		"\u00c2\7\66\2\2\u00c2\33\3\2\2\2\u00c3\u00c4\7/\2\2\u00c4\u00c8\5\2\2"+
		"\2\u00c5\u00c6\7\60\2\2\u00c6\u00c8\5\2\2\2\u00c7\u00c3\3\2\2\2\u00c7"+
		"\u00c5\3\2\2\2\u00c8\35\3\2\2\2\u00c9\u00d0\5\26\f\2\u00ca\u00cb\5\24"+
		"\13\2\u00cb\u00cc\7\65\2\2\u00cc\u00cd\7\66\2\2\u00cd\u00d0\3\2\2\2\u00ce"+
		"\u00d0\7.\2\2\u00cf\u00c9\3\2\2\2\u00cf\u00ca\3\2\2\2\u00cf\u00ce\3\2"+
		"\2\2\u00d0\37\3\2\2\2\u00d1\u00d2\5\24\13\2\u00d2\u00d3\7?\2\2\u00d3\u00d5"+
		"\7\63\2\2\u00d4\u00d6\5\"\22\2\u00d5\u00d4\3\2\2\2\u00d5\u00d6\3\2\2\2"+
		"\u00d6\u00d7\3\2\2\2\u00d7\u00d8\7\64\2\2\u00d8\u00d9\7)\2\2\u00d9\u00da"+
		"\5\6\4\2\u00da\u00db\7\'\2\2\u00db!\3\2\2\2\u00dc\u00dd\5\24\13\2\u00dd"+
		"\u00e4\7?\2\2\u00de\u00df\7>\2\2\u00df\u00e0\5\24\13\2\u00e0\u00e1\7?"+
		"\2\2\u00e1\u00e3\3\2\2\2\u00e2\u00de\3\2\2\2\u00e3\u00e6\3\2\2\2\u00e4"+
		"\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5#\3\2\2\2\u00e6\u00e4\3\2\2\2"+
		"\u00e7\u00eb\7?\2\2\u00e8\u00eb\5\30\r\2\u00e9\u00eb\5\34\17\2\u00ea\u00e7"+
		"\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00e9\3\2\2\2\u00eb%\3\2\2\2\u00ec"+
		"\u0105\5\2\2\2\u00ed\u0105\5\32\16\2\u00ee\u00ef\7\61\2\2\u00ef\u00f0"+
		"\7\63\2\2\u00f0\u00f1\5\2\2\2\u00f1\u00f2\7>\2\2\u00f2\u00f3\5\2\2\2\u00f3"+
		"\u00f4\7\64\2\2\u00f4\u0105\3\2\2\2\u00f5\u0105\5\34\17\2\u00f6\u00f7"+
		"\7(\2\2\u00f7\u00f8\7?\2\2\u00f8\u0101\7\63\2\2\u00f9\u00fe\5\2\2\2\u00fa"+
		"\u00fb\7>\2\2\u00fb\u00fd\5\2\2\2\u00fc\u00fa\3\2\2\2\u00fd\u0100\3\2"+
		"\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0102\3\2\2\2\u0100"+
		"\u00fe\3\2\2\2\u0101\u00f9\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0103\3\2"+
		"\2\2\u0103\u0105\7\64\2\2\u0104\u00ec\3\2\2\2\u0104\u00ed\3\2\2\2\u0104"+
		"\u00ee\3\2\2\2\u0104\u00f5\3\2\2\2\u0104\u00f6\3\2\2\2\u0105\'\3\2\2\2"+
		"\u0106\u010a\7&\2\2\u0107\u0109\5 \21\2\u0108\u0107\3\2\2\2\u0109\u010c"+
		"\3\2\2\2\u010a\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u010d\3\2\2\2\u010c"+
		"\u010a\3\2\2\2\u010d\u010e\5\6\4\2\u010e\u010f\7\'\2\2\u010f\u0110\7\2"+
		"\2\3\u0110)\3\2\2\2\26:OQ\177\u0086\u008a\u00a1\u00a8\u00b4\u00bc\u00bf"+
		"\u00c7\u00cf\u00d5\u00e4\u00ea\u00fe\u0101\u0104\u010a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
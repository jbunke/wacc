// Generated from ./WACCLexer.g4 by ANTLR 4.7
package antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class WACCLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"WS", "LINE_COMMENT", "SEMI_COLON", "ASSIGN", "NOT", "LEN", "ORD", "CHR", 
		"TIMES", "DIVIDE", "MOD", "PLUS", "MINUS", "GREATER_THAN", "GREATER_THAN_OR_EQUAL", 
		"LESS_THAN", "LESS_THAN_OR_EQUAL", "EQUAL", "NOT_EQUAL", "AND", "OR", 
		"SKP", "READ", "FREE", "RETURN", "EXIT", "PRINT", "PRINTLN", "IF", "THEN", 
		"ELSE", "FI", "WHILE", "DO", "DONE", "BEGIN", "END", "CALL", "IS", "INT", 
		"BOOL", "CHAR", "STRING", "PAIR", "FST", "SND", "NEW_PAIR", "DIGIT", "INT_LIT", 
		"OPEN_PARENTHESIS", "CLOSE_PARENTHESIS", "OPEN_BRACKET", "CLOSE_BRACKET", 
		"CHAR_QUOTE", "STR_QUOTE", "BOOL_LITER", "RESTRICTED_ASCII", "NULL", "STRING_LITERAL", 
		"CHARACTER", "CHAR_LIT", "ESC_CHAR", "COMMA", "IDENTIFIER"
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


	public WACCLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "WACCLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2?\u018b\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\3\2\6\2\u0085\n\2\r\2\16\2\u0086\3\2\3\2\3\3"+
		"\3\3\7\3\u008d\n\3\f\3\16\3\u0090\13\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f"+
		"\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3"+
		"\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3"+
		"\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3"+
		"\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3"+
		"\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3"+
		"\36\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3\"\3"+
		"\"\3\"\3#\3#\3#\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3\'\3\'\3"+
		"\'\3\'\3\'\3(\3(\3(\3)\3)\3)\3)\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3,\3,\3"+
		",\3,\3,\3,\3,\3-\3-\3-\3-\3-\3.\3.\3.\3.\3/\3/\3/\3/\3\60\3\60\3\60\3"+
		"\60\3\60\3\60\3\60\3\60\3\61\3\61\3\62\6\62\u014c\n\62\r\62\16\62\u014d"+
		"\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\39\39\39\3"+
		"9\39\39\39\39\59\u0165\n9\3:\3:\3;\3;\3;\3;\3;\3<\3<\3<\7<\u0171\n<\f"+
		"<\16<\u0174\13<\3<\3<\3=\3=\5=\u017a\n=\3>\3>\3>\3>\3?\3?\3?\3@\3@\3A"+
		"\3A\7A\u0187\nA\fA\16A\u018a\13A\2\2B\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.["+
		"/]\60_\61a\2c\62e\63g\64i\65k\66m\67o8q9s\2u:w;y\2{<}=\177>\u0081?\3\2"+
		"\b\4\2\13\f\"\"\4\2\f\f\17\17\5\2$$))^^\13\2$$))\62\62^^ddhhppttvv\5\2"+
		"C\\aac|\6\2\62;C\\aac|\2\u018f\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"+
		"\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2"+
		"\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2"+
		"+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2"+
		"\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2"+
		"C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3"+
		"\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2"+
		"\2\2]\3\2\2\2\2_\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2"+
		"k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2{\3"+
		"\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\3\u0084\3\2\2\2\5\u008a"+
		"\3\2\2\2\7\u0093\3\2\2\2\t\u0095\3\2\2\2\13\u0097\3\2\2\2\r\u0099\3\2"+
		"\2\2\17\u009d\3\2\2\2\21\u00a1\3\2\2\2\23\u00a5\3\2\2\2\25\u00a7\3\2\2"+
		"\2\27\u00a9\3\2\2\2\31\u00ab\3\2\2\2\33\u00ad\3\2\2\2\35\u00af\3\2\2\2"+
		"\37\u00b1\3\2\2\2!\u00b4\3\2\2\2#\u00b6\3\2\2\2%\u00b9\3\2\2\2\'\u00bc"+
		"\3\2\2\2)\u00bf\3\2\2\2+\u00c2\3\2\2\2-\u00c5\3\2\2\2/\u00ca\3\2\2\2\61"+
		"\u00cf\3\2\2\2\63\u00d4\3\2\2\2\65\u00db\3\2\2\2\67\u00e0\3\2\2\29\u00e6"+
		"\3\2\2\2;\u00ee\3\2\2\2=\u00f1\3\2\2\2?\u00f6\3\2\2\2A\u00fb\3\2\2\2C"+
		"\u00fe\3\2\2\2E\u0104\3\2\2\2G\u0107\3\2\2\2I\u010c\3\2\2\2K\u0112\3\2"+
		"\2\2M\u0116\3\2\2\2O\u011b\3\2\2\2Q\u011e\3\2\2\2S\u0122\3\2\2\2U\u0127"+
		"\3\2\2\2W\u012c\3\2\2\2Y\u0133\3\2\2\2[\u0138\3\2\2\2]\u013c\3\2\2\2_"+
		"\u0140\3\2\2\2a\u0148\3\2\2\2c\u014b\3\2\2\2e\u014f\3\2\2\2g\u0151\3\2"+
		"\2\2i\u0153\3\2\2\2k\u0155\3\2\2\2m\u0157\3\2\2\2o\u0159\3\2\2\2q\u0164"+
		"\3\2\2\2s\u0166\3\2\2\2u\u0168\3\2\2\2w\u016d\3\2\2\2y\u0179\3\2\2\2{"+
		"\u017b\3\2\2\2}\u017f\3\2\2\2\177\u0182\3\2\2\2\u0081\u0184\3\2\2\2\u0083"+
		"\u0085\t\2\2\2\u0084\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0084\3\2"+
		"\2\2\u0086\u0087\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\b\2\2\2\u0089"+
		"\4\3\2\2\2\u008a\u008e\7%\2\2\u008b\u008d\n\3\2\2\u008c\u008b\3\2\2\2"+
		"\u008d\u0090\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0091"+
		"\3\2\2\2\u0090\u008e\3\2\2\2\u0091\u0092\b\3\2\2\u0092\6\3\2\2\2\u0093"+
		"\u0094\7=\2\2\u0094\b\3\2\2\2\u0095\u0096\7?\2\2\u0096\n\3\2\2\2\u0097"+
		"\u0098\7#\2\2\u0098\f\3\2\2\2\u0099\u009a\7n\2\2\u009a\u009b\7g\2\2\u009b"+
		"\u009c\7p\2\2\u009c\16\3\2\2\2\u009d\u009e\7q\2\2\u009e\u009f\7t\2\2\u009f"+
		"\u00a0\7f\2\2\u00a0\20\3\2\2\2\u00a1\u00a2\7e\2\2\u00a2\u00a3\7j\2\2\u00a3"+
		"\u00a4\7t\2\2\u00a4\22\3\2\2\2\u00a5\u00a6\7,\2\2\u00a6\24\3\2\2\2\u00a7"+
		"\u00a8\7\61\2\2\u00a8\26\3\2\2\2\u00a9\u00aa\7\'\2\2\u00aa\30\3\2\2\2"+
		"\u00ab\u00ac\7-\2\2\u00ac\32\3\2\2\2\u00ad\u00ae\7/\2\2\u00ae\34\3\2\2"+
		"\2\u00af\u00b0\7@\2\2\u00b0\36\3\2\2\2\u00b1\u00b2\7@\2\2\u00b2\u00b3"+
		"\7?\2\2\u00b3 \3\2\2\2\u00b4\u00b5\7>\2\2\u00b5\"\3\2\2\2\u00b6\u00b7"+
		"\7>\2\2\u00b7\u00b8\7?\2\2\u00b8$\3\2\2\2\u00b9\u00ba\7?\2\2\u00ba\u00bb"+
		"\7?\2\2\u00bb&\3\2\2\2\u00bc\u00bd\7#\2\2\u00bd\u00be\7?\2\2\u00be(\3"+
		"\2\2\2\u00bf\u00c0\7(\2\2\u00c0\u00c1\7(\2\2\u00c1*\3\2\2\2\u00c2\u00c3"+
		"\7~\2\2\u00c3\u00c4\7~\2\2\u00c4,\3\2\2\2\u00c5\u00c6\7u\2\2\u00c6\u00c7"+
		"\7m\2\2\u00c7\u00c8\7k\2\2\u00c8\u00c9\7r\2\2\u00c9.\3\2\2\2\u00ca\u00cb"+
		"\7t\2\2\u00cb\u00cc\7g\2\2\u00cc\u00cd\7c\2\2\u00cd\u00ce\7f\2\2\u00ce"+
		"\60\3\2\2\2\u00cf\u00d0\7h\2\2\u00d0\u00d1\7t\2\2\u00d1\u00d2\7g\2\2\u00d2"+
		"\u00d3\7g\2\2\u00d3\62\3\2\2\2\u00d4\u00d5\7t\2\2\u00d5\u00d6\7g\2\2\u00d6"+
		"\u00d7\7v\2\2\u00d7\u00d8\7w\2\2\u00d8\u00d9\7t\2\2\u00d9\u00da\7p\2\2"+
		"\u00da\64\3\2\2\2\u00db\u00dc\7g\2\2\u00dc\u00dd\7z\2\2\u00dd\u00de\7"+
		"k\2\2\u00de\u00df\7v\2\2\u00df\66\3\2\2\2\u00e0\u00e1\7r\2\2\u00e1\u00e2"+
		"\7t\2\2\u00e2\u00e3\7k\2\2\u00e3\u00e4\7p\2\2\u00e4\u00e5\7v\2\2\u00e5"+
		"8\3\2\2\2\u00e6\u00e7\7r\2\2\u00e7\u00e8\7t\2\2\u00e8\u00e9\7k\2\2\u00e9"+
		"\u00ea\7p\2\2\u00ea\u00eb\7v\2\2\u00eb\u00ec\7n\2\2\u00ec\u00ed\7p\2\2"+
		"\u00ed:\3\2\2\2\u00ee\u00ef\7k\2\2\u00ef\u00f0\7h\2\2\u00f0<\3\2\2\2\u00f1"+
		"\u00f2\7v\2\2\u00f2\u00f3\7j\2\2\u00f3\u00f4\7g\2\2\u00f4\u00f5\7p\2\2"+
		"\u00f5>\3\2\2\2\u00f6\u00f7\7g\2\2\u00f7\u00f8\7n\2\2\u00f8\u00f9\7u\2"+
		"\2\u00f9\u00fa\7g\2\2\u00fa@\3\2\2\2\u00fb\u00fc\7h\2\2\u00fc\u00fd\7"+
		"k\2\2\u00fdB\3\2\2\2\u00fe\u00ff\7y\2\2\u00ff\u0100\7j\2\2\u0100\u0101"+
		"\7k\2\2\u0101\u0102\7n\2\2\u0102\u0103\7g\2\2\u0103D\3\2\2\2\u0104\u0105"+
		"\7f\2\2\u0105\u0106\7q\2\2\u0106F\3\2\2\2\u0107\u0108\7f\2\2\u0108\u0109"+
		"\7q\2\2\u0109\u010a\7p\2\2\u010a\u010b\7g\2\2\u010bH\3\2\2\2\u010c\u010d"+
		"\7d\2\2\u010d\u010e\7g\2\2\u010e\u010f\7i\2\2\u010f\u0110\7k\2\2\u0110"+
		"\u0111\7p\2\2\u0111J\3\2\2\2\u0112\u0113\7g\2\2\u0113\u0114\7p\2\2\u0114"+
		"\u0115\7f\2\2\u0115L\3\2\2\2\u0116\u0117\7e\2\2\u0117\u0118\7c\2\2\u0118"+
		"\u0119\7n\2\2\u0119\u011a\7n\2\2\u011aN\3\2\2\2\u011b\u011c\7k\2\2\u011c"+
		"\u011d\7u\2\2\u011dP\3\2\2\2\u011e\u011f\7k\2\2\u011f\u0120\7p\2\2\u0120"+
		"\u0121\7v\2\2\u0121R\3\2\2\2\u0122\u0123\7d\2\2\u0123\u0124\7q\2\2\u0124"+
		"\u0125\7q\2\2\u0125\u0126\7n\2\2\u0126T\3\2\2\2\u0127\u0128\7e\2\2\u0128"+
		"\u0129\7j\2\2\u0129\u012a\7c\2\2\u012a\u012b\7t\2\2\u012bV\3\2\2\2\u012c"+
		"\u012d\7u\2\2\u012d\u012e\7v\2\2\u012e\u012f\7t\2\2\u012f\u0130\7k\2\2"+
		"\u0130\u0131\7p\2\2\u0131\u0132\7i\2\2\u0132X\3\2\2\2\u0133\u0134\7r\2"+
		"\2\u0134\u0135\7c\2\2\u0135\u0136\7k\2\2\u0136\u0137\7t\2\2\u0137Z\3\2"+
		"\2\2\u0138\u0139\7h\2\2\u0139\u013a\7u\2\2\u013a\u013b\7v\2\2\u013b\\"+
		"\3\2\2\2\u013c\u013d\7u\2\2\u013d\u013e\7p\2\2\u013e\u013f\7f\2\2\u013f"+
		"^\3\2\2\2\u0140\u0141\7p\2\2\u0141\u0142\7g\2\2\u0142\u0143\7y\2\2\u0143"+
		"\u0144\7r\2\2\u0144\u0145\7c\2\2\u0145\u0146\7k\2\2\u0146\u0147\7t\2\2"+
		"\u0147`\3\2\2\2\u0148\u0149\4\62;\2\u0149b\3\2\2\2\u014a\u014c\5a\61\2"+
		"\u014b\u014a\3\2\2\2\u014c\u014d\3\2\2\2\u014d\u014b\3\2\2\2\u014d\u014e"+
		"\3\2\2\2\u014ed\3\2\2\2\u014f\u0150\7*\2\2\u0150f\3\2\2\2\u0151\u0152"+
		"\7+\2\2\u0152h\3\2\2\2\u0153\u0154\7]\2\2\u0154j\3\2\2\2\u0155\u0156\7"+
		"_\2\2\u0156l\3\2\2\2\u0157\u0158\7)\2\2\u0158n\3\2\2\2\u0159\u015a\7$"+
		"\2\2\u015ap\3\2\2\2\u015b\u015c\7v\2\2\u015c\u015d\7t\2\2\u015d\u015e"+
		"\7w\2\2\u015e\u0165\7g\2\2\u015f\u0160\7h\2\2\u0160\u0161\7c\2\2\u0161"+
		"\u0162\7n\2\2\u0162\u0163\7u\2\2\u0163\u0165\7g\2\2\u0164\u015b\3\2\2"+
		"\2\u0164\u015f\3\2\2\2\u0165r\3\2\2\2\u0166\u0167\n\4\2\2\u0167t\3\2\2"+
		"\2\u0168\u0169\7p\2\2\u0169\u016a\7w\2\2\u016a\u016b\7n\2\2\u016b\u016c"+
		"\7n\2\2\u016cv\3\2\2\2\u016d\u0172\7$\2\2\u016e\u0171\5s:\2\u016f\u0171"+
		"\5}?\2\u0170\u016e\3\2\2\2\u0170\u016f\3\2\2\2\u0171\u0174\3\2\2\2\u0172"+
		"\u0170\3\2\2\2\u0172\u0173\3\2\2\2\u0173\u0175\3\2\2\2\u0174\u0172\3\2"+
		"\2\2\u0175\u0176\7$\2\2\u0176x\3\2\2\2\u0177\u017a\5s:\2\u0178\u017a\5"+
		"}?\2\u0179\u0177\3\2\2\2\u0179\u0178\3\2\2\2\u017az\3\2\2\2\u017b\u017c"+
		"\7)\2\2\u017c\u017d\5y=\2\u017d\u017e\7)\2\2\u017e|\3\2\2\2\u017f\u0180"+
		"\7^\2\2\u0180\u0181\t\5\2\2\u0181~\3\2\2\2\u0182\u0183\7.\2\2\u0183\u0080"+
		"\3\2\2\2\u0184\u0188\t\6\2\2\u0185\u0187\t\7\2\2\u0186\u0185\3\2\2\2\u0187"+
		"\u018a\3\2\2\2\u0188\u0186\3\2\2\2\u0188\u0189\3\2\2\2\u0189\u0082\3\2"+
		"\2\2\u018a\u0188\3\2\2\2\13\2\u0086\u008e\u014d\u0164\u0170\u0172\u0179"+
		"\u0188\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
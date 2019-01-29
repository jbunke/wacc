package frontend.lexer;

public class Token {
  private String value;
  private String ident;
  private int number;
  private Type type;

  enum Type {
    NUMBER,
    IDENTIFIER,
    CHR_LIT,
    STR_LIT,
    COMMENT,
    BEGIN, IS, END,
    SKIP,
    READ,
    FREE,
    RETURN,
    EXIT,
    PRINT,
    PRINTLN,
    IF, THEN, ELSE, FI,
    WHILE, DO, DONE,
    NEWPAIR,
    CALL,
    FST, SND,
    INT, BOOL, CHAR, STRING,
    PAIR,
    LEN, ORD, CHR,
    TRUE, FALSE,
    NULL,
    // Punctuation
    BRACKET_OPEN, BRACKET_CLOSE,
    SQUARE_OPEN, SQUARE_CLOSE,
    COMMA, PERIOD, SEMICOLON,
    // OPERATORS
    ASSIGN,
    NOT, AND, OR,
    MINUS, PLUS, MULT, DIV, MOD,
    GR, LT, GR_EQ, LT_EQ, EQ, NEQ,
    // Special
    UNMATCHABLE_TOKEN,
  }

  private Token(Type type, String value) {
    this.type = type;
    this.value = value;

    switch (type) {
      case NUMBER:
        number = Integer.parseInt(value);
        break;
      case IDENTIFIER:
        ident = value;
        break;
      case CHR_LIT:
        this.value = value;
    }
  }

  // Use for all keyword tokens
  static Token keywordToken(String kw) {
    try {
      Type tokenType = Type.valueOf(kw.toUpperCase());
      return new Token(tokenType, null);
    } catch (IllegalArgumentException iae) {
      return null;
    }
  }

  // Use for IDENTIFIER, NUMBER, CHR_LIT, STR_LIT tokens
  static Token typeToken(Type type, String value) {
    return new Token(type, value);
  }

  int getNumber() {
    assert (type == Type.NUMBER);
    return number;
  }

  String getValue() {
    assert (type == Type.CHR_LIT);
    return value;
  }

  String getIdent() {
    assert (type == Type.IDENTIFIER);
    return ident;
  }

  Type getType() { return type; }

  @Override
  public String toString() {
    StringBuilder ts = new StringBuilder(type.toString());

    switch (type) {
      case NUMBER:
        ts.append(" ");
        ts.append(number);
        break;
      case IDENTIFIER:
        ts.append(" ");
        ts.append(ident);
        break;
      case CHR_LIT:
      case STR_LIT:
      case COMMENT:
        ts.append(" ");
        ts.append(value);
        break;
    }

    return ts.toString();
  }
}

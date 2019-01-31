package frontend.lexer;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

public class LexerTests {

  @Test
  public void arrayWACCLexerCorrectness() {
    Lexer arrayWACC = new Lexer(
            "..\\wacc_examples\\valid\\array\\array.wacc");

    assertEquals(arrayWACC.next(),
            Token.typeToken(Token.Type.COMMENT, null));

    // Loop past trivial tokens
    for (int i = 0; i < 3; i++) {
      arrayWACC.next();
    }

    assertEquals(arrayWACC.next(), Token.keywordToken("begin"));
    assertEquals(arrayWACC.next(), Token.keywordToken("int"));
    assertEquals(arrayWACC.next(),
            Token.typeToken(Token.Type.SQUARE_OPEN, null));
    assertEquals(arrayWACC.next(),
            Token.typeToken(Token.Type.SQUARE_CLOSE, null));
    assertEquals(arrayWACC.next(),
            Token.typeToken(Token.Type.IDENTIFIER, "a"));
    assertEquals(arrayWACC.next(),
            Token.typeToken(Token.Type.ASSIGN, null));
    assertEquals(arrayWACC.next(),
            Token.typeToken(Token.Type.SQUARE_OPEN, null));
    assertEquals(arrayWACC.next(),
            Token.typeToken(Token.Type.NUMBER, "0"));
    assertEquals(arrayWACC.next(),
            Token.typeToken(Token.Type.COMMA, null));

    for (int i = 0; i < 17; i++) {
      arrayWACC.next();
    }

    assertEquals(arrayWACC.next(),
            Token.typeToken(Token.Type.SQUARE_CLOSE, null));
    assertEquals(arrayWACC.next(),
            Token.typeToken(Token.Type.SEMICOLON, null));

    for (int i = 0; i < 28; i++) {
      arrayWACC.next();
    }

    // String literal testing
    assertEquals(arrayWACC.next(),
            Token.typeToken(Token.Type.STR_LIT, "\" = {\""));
  }
}

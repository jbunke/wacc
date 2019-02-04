package frontend.lexer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Lexer {
  private static List<String> keywords = Arrays.asList(
          "begin", "is", "end", "skip", "read", "free", "return", "exit",
          "print", "println", "if", "then", "else", "fi", "while", "do", "done",
          "newpair", "call", "fst", "snd", "int", "bool", "char", "string",
          "pair", "len", "ord", "chr", "true", "false", "null"
  );

  private static Map<String, Token.Type> punctuation = Map.ofEntries(
          Map.entry("(", Token.Type.BRACKET_OPEN),
          Map.entry(")", Token.Type.BRACKET_CLOSE),
          Map.entry("[", Token.Type.SQUARE_OPEN),
          Map.entry("]", Token.Type.SQUARE_CLOSE),
          Map.entry(".", Token.Type.PERIOD),
          Map.entry(",", Token.Type.COMMA),
          Map.entry(";", Token.Type.SEMICOLON),

          Map.entry("=", Token.Type.ASSIGN),
          Map.entry("==", Token.Type.EQ),
          Map.entry("!=", Token.Type.NEQ),
          Map.entry(">", Token.Type.GR),
          Map.entry(">=", Token.Type.GR_EQ),
          Map.entry("<", Token.Type.LT),
          Map.entry("<=", Token.Type.LT_EQ),
          Map.entry("!", Token.Type.NOT),
          Map.entry("&&", Token.Type.AND),
          Map.entry("||", Token.Type.OR),
          Map.entry("+", Token.Type.PLUS),
          Map.entry("-", Token.Type.MINUS),
          Map.entry("*", Token.Type.MULT),
          Map.entry("/", Token.Type.DIV),
          Map.entry("%", Token.Type.MOD)
  );

  private List<Token> tokens;
  private int index;

  public Lexer(String filename) {
    if (!filename.endsWith(".wacc")) {
      throw new IllegalArgumentException("Invalid! File type must be a .wacc file");
    }
    String fileText = readFile(filename);
    this.tokens = lexical_analysis(fileText);
  }

  private String readFile(String filename) {
    StringBuilder file = new StringBuilder("");
    try {
      FileReader fr = new FileReader(filename);
      BufferedReader br = new BufferedReader(fr);
      String line;

      while ((line = br.readLine()) != null) {
        file.append(line);
        file.append("\n");
      }

    } catch (FileNotFoundException fnfe) {
      fnfe.printStackTrace();
      System.out.print("File \"" + filename + "\" not found.");
      System.exit(1);
    } catch (IOException ioe) {
      ioe.printStackTrace();
      System.out.print("Buffered reader readLine() failed in file \"" +
              filename + "\"");
      System.exit(1);
    }
    return file.toString();
  }

  private Token tokenise(String toMatch) {
    if (keywords.contains(toMatch)) {
      return Token.keywordToken(toMatch);
    } else if (punctuation.containsKey(toMatch)) {
      Token.Type punctType = punctuation.get(toMatch);
      return Token.typeToken(punctType, null);
    } else {
      char first = toMatch.charAt(0);

      if (first == '\'') {
        return Token.typeToken(Token.Type.CHR_LIT, toMatch);
      } else if (first == '"') {
        return Token.typeToken(Token.Type.STR_LIT, toMatch);
      } else if (first == '#') {
        return Token.typeToken(Token.Type.COMMENT, toMatch);
      } else if ((first >= '0' && first <= '9') ||
              ((first == '-' || first == '+') && toMatch.length() > 1)) {
        return Token.typeToken(Token.Type.NUMBER, toMatch);
      } else if ((first >= 'A' && first <= 'Z') ||
              (first >= 'a' && first <= 'z') || first == '_') {
        return Token.typeToken(Token.Type.IDENTIFIER, toMatch);
      }

      // TODO: Throw exception/error for unmatched token
      return Token.typeToken(Token.Type.UNMATCHABLE_TOKEN, null);
    }
  }

  private List<Token> lexical_analysis(String fileText) {
    List<Token> res = new ArrayList<>();
    StringBuilder current = new StringBuilder();

    for (int i = 0; i < fileText.length(); i++) {
      char c = fileText.charAt(i);
      char n = ' ';
      if (i + 1 < fileText.length())
        n = fileText.charAt(i + 1);

      if (current.length() > 0) {
        // Case where current token is non-empty

        if (current.charAt(0) == '#') {
          if (!(c == '\n')) {
            current.append(c);
          } else {
            res.add(tokenise(current.toString()));
            current = new StringBuilder();
          }
        } else if (current.charAt(0) == '"' | current.charAt(0) == '\'') {
          assert (true); // TODO: Non-escaped chars
          char quotationMark = current.charAt(0);
          current.append(c);
          if (c == quotationMark) {
            res.add(tokenise(current.toString()));
            current = new StringBuilder();
          }
        } else {
          if ((c >= '0' && c <= '9') || c == '_' || (c >= 'A' && c <= 'Z')
                  || (c >= 'a' && c <= 'z')) {
            // part of a continuing identifier, number, keyword, etc.
            current.append(c);
          } else if (c == ' ' || c == '\n' || c == '\r' || c == '\f') {
            res.add(tokenise(current.toString()));
            current = new StringBuilder();
          } else {
            res.add(tokenise(current.toString()));
            current = new StringBuilder();
            current.append(c);
            res.add(tokenise(current.toString()));
            current = new StringBuilder();
          }
        }
      } else {
        // Case where current token is empty

        if (!(c == ' ' || c == '\n' || c == '\r' || c == '\f')) {
          current.append(c);
          if (!((c >= '0' && c <= '9') || c == '_' || (c >= 'A' && c <= 'Z')
                  || (c >= 'a' && c <= 'z') || c == '"' || c == '\''
                  || c == '#' || ((c == '+' || c == '-')
                  && (n >= '0' && n <= '9')))) {
            /* If the character cannot be followed by anything in a token,
             * break the token off there and reset the current token */
            if (n == '=' && (c == '>' || c == '<' || c == '=' || c == '!')) {
              current.append(n);
              i++;
            } else if ((c == '&' || c == '|') && c == n) {
              current.append(n);
              i++;
            }
            res.add(tokenise(current.toString()));
            current = new StringBuilder();
          }
        }
      }
    }

    return res;
  }

  public boolean hasNext() {
    return index < tokens.size();
  }

  public Token next() {
    assert (hasNext());
    Token token =  tokens.get(index);
    index++;
    return token;
  }
}

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Class maintains a log of syntax errors which are caught during parsing.
 * This log can be queried to detect the presence of syntax errors and
 * retrieve the cause of said errors.
 */
public class WACCParserErrorListener extends BaseErrorListener {

  private List<String> syntaxErrors = new ArrayList<>();

  // Override default syntax error reporting from ANTLR

  @Override
  public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                          int line, int charPositionInLine, String msg,
                          RecognitionException e) {
    syntaxErrors.add("Syntax Error: line " + line + ":"
            + charPositionInLine + " -> " + msg);
  }

  boolean hasError() {
    return !syntaxErrors.isEmpty();
  }

  List<String> getSyntaxErrors() {
    return syntaxErrors;
  }
}

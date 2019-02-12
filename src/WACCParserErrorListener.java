import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.RecognitionException;

public class WACCParserErrorListener extends BaseErrorListener {

  private List<String> syntaxErrors = new ArrayList<>();

  @Override
  public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                          int line, int charPositionInLine, String msg,
                          RecognitionException e)
  {
    syntaxErrors.add("Syntax Error: line " + line + ":"
                     + charPositionInLine + " -> " + msg);
  }

  public boolean hasError() {
    return !syntaxErrors.isEmpty();
  }

  public List<String> getSyntaxErrors() {
    return syntaxErrors;
  }
}

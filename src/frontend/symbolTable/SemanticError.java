package frontend.symbolTable;

import static shell.WACCShell.ANSI_RED;
import static shell.WACCShell.ANSI_RESET;

import java.io.PrintStream;

public class SemanticError {
  private final String errorMessage;

  public SemanticError(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public void print(PrintStream stream) {
    stream.println(ANSI_RED + "(code 200) Semantic error: " + errorMessage + ANSI_RESET);
  }
}

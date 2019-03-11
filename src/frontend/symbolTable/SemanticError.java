package frontend.symbolTable;

import java.io.PrintStream;

public class SemanticError {
  private final String errorMessage;

  public SemanticError(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public void print(PrintStream stream) {
    stream.println("Semantic Error 200: " + errorMessage);
  }
}

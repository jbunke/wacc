package shell;

import antlr.WACCLexer;
import antlr.WACCParser;
import frontend.Visitor;
import frontend.WACCParserErrorListener;
import frontend.abstractSyntaxTree.expressions.ExpressionNode;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.util.Scanner;

public class WACCShell {
  private final static String QUIT_STRING = ":q";
  private final static String PROMPTER = "> ";
  private final static String CONTINUE = "| ";

  private static SymbolTable symbolTable;

  public static void main(String[] args) {
    symbolTable = null;

    Scanner in = new Scanner(System.in);
    prompt();
    String line = in.nextLine();

    while (!line.equals(QUIT_STRING)) {
      if (!line.isEmpty()) {
        processCommand(line);
      }

      prompt();
      line = in.nextLine();
    }
  }

  private static void processCommand(String line) {
    CharStream input = CharStreams.fromString(line);
    WACCLexer lexer = new WACCLexer(input);
    lexer.removeErrorListeners();
    TokenStream tokens = new CommonTokenStream(lexer);
    WACCParser parser = new WACCParser(tokens);
    parser.removeErrorListeners();

    WACCParserErrorListener synErrors =
            new WACCParserErrorListener();
    parser.addErrorListener(synErrors);

    WACCParser.CommandContext parseTree = parser.command();

    if (synErrors.hasError()) {
      for (String error : synErrors.getSyntaxErrors()) {
        System.out.println(error);
      }
      return;
    }

    Visitor visitor = new Visitor();
    ExpressionNode expression = (ExpressionNode) visitor.visit(parseTree);
    SemanticErrorList semErrors = new SemanticErrorList();

    if (symbolTable == null) {
      symbolTable = new SymbolTable(null, expression);
    }
    expression.semanticCheck(symbolTable, semErrors);

    if (semErrors.errorsExist()) {
      semErrors.printErrors(System.out);
      return;
    }

    Object evaluated = expression.evaluate(symbolTable);
    System.out.println(evaluated);
  }

  private static void prompt() {
    System.out.print(PROMPTER);
  }
}

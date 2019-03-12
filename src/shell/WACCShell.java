package shell;

import antlr.WACCLexer;
import antlr.WACCParser;
import frontend.Visitor;
import frontend.WACCParserErrorListener;
import frontend.abstractSyntaxTree.Node;
import frontend.abstractSyntaxTree.expressions.ExpressionNode;
import frontend.abstractSyntaxTree.statements.StatementNode;
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
    Node command = visitor.visit(parseTree);

    if (command instanceof ExpressionNode) {
      processExpression((ExpressionNode) command);
    } else if (command instanceof StatementNode) {
      processStatement((StatementNode) command);
    }
  }

  private static void processStatement(StatementNode statement) {
    if (semErrorCheck(statement)) return;
    /* symbol table should now have populated any variables
     * declared in statement */

    statement.applyStatement(symbolTable);
  }

  private static void processExpression(ExpressionNode expression) {
    if (semErrorCheck(expression)) return;

    Object evaluated = expression.evaluate(symbolTable);
    System.out.println(evaluated);
  }

  private static boolean semErrorCheck(Node node) {
    SemanticErrorList semErrors = new SemanticErrorList();

    if (symbolTable == null) {
      symbolTable = new SymbolTable(null, node);
    }
    node.semanticCheck(symbolTable, semErrors);

    if (semErrors.errorsExist()) {
      semErrors.printErrors(System.out);
      return true;
    }
    return false;
  }

  private static void prompt() {
    System.out.print(PROMPTER);
  }
}

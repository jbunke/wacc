package shell;

import antlr.WACCLexer;
import antlr.WACCParser;
import frontend.Visitor;
import frontend.WACCParserErrorListener;
import frontend.abstractSyntaxTree.Node;
import frontend.abstractSyntaxTree.assignment.AssignRHS;
import frontend.abstractSyntaxTree.statements.StatementNode;
import frontend.abstractSyntaxTree.typeNodes.FunctionDefinitionNode;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import shell.cliProcessing.CommandProcessing;
import shell.cliProcessing.SpecialCommands;

import java.util.Scanner;

public class WACCShell {

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_PURPLE = "\u001B[35m";

  private final static String QUIT_STRING = ":q";

  private final static String COMMENT = "#";
  private final static String PROMPTER = "> ";
  private final static String TAB_CHAR = "\t";
  private final static String CONTINUE = "| ";

  public static SymbolTable symbolTable;
  private static Heap heap;

  public static Scanner in;

  public static void main(String[] args) {
    symbolTable = null;
    heap = new Heap();

    in = new Scanner(System.in);

    startUp();
    prompt();
    String line = CommandProcessing.acquireCommand(in.nextLine(), 0);
    while(!line.equals(QUIT_STRING)) {
      if (!line.isEmpty() && SpecialCommands.commandMatchCheck(line) &&
              !line.startsWith(COMMENT)) {
        processCommand(line);
      }
      prompt();
      line = CommandProcessing.acquireCommand(in.nextLine(), 0);
    }
    System.out.print(ANSI_GREEN);
    System.out.print("\nThank you for using our interactive shell!");
  }

  private static void startUp() {
    System.out.print(ANSI_PURPLE);
    System.out.println("\n-- WELCOME TO THE WACC INTERACTIVE SHELL --\n");
    System.out.print(ANSI_GREEN);
    System.out.println("Type \":h\" for help");
    System.out.println("Type \"" + QUIT_STRING + "\" to quit\n");
    System.out.print(ANSI_RESET);
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
        System.out.println(ANSI_RED + error + ANSI_RESET);
      }
      return;
    }

    Visitor visitor = new Visitor();
    Node command = visitor.visit(parseTree);

    if (command instanceof AssignRHS) {
      processRHS((AssignRHS) command);
    } else if (command instanceof StatementNode) {
      processStatement((StatementNode) command);
    } else if (command instanceof FunctionDefinitionNode) {
      processFunction((FunctionDefinitionNode) command);
    }
  }

  private static void processFunction(FunctionDefinitionNode function) {
    if (semErrorCheck(function)) return;

    symbolTable.add(function.getIdentifier(), function);
  }

  private static void processStatement(StatementNode statement) {
    if (semErrorCheck(statement)) return;
    /* symbol table should now have populated any variables
     * declared in statement */

    statement.applyStatement(symbolTable, heap);
  }

  private static void processRHS(AssignRHS rhs) {
    if (semErrorCheck(rhs)) return;

    Object evaluated = rhs.evaluate(symbolTable, heap);
    System.out.println(evaluated);
  }

  public static boolean semErrorCheck(Node node) {
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

  public static void promptWithIndent(int level) {
    StringBuilder builder = new StringBuilder(CONTINUE);

    for (int i = 0; i < level; i++) {
      builder.append(TAB_CHAR);
    }

    System.out.print(builder.toString());
  }
}

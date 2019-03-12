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

import java.io.*;
import java.util.Scanner;

public class WACCShell {

  private final static String QUIT_STRING = ":q";
  private final static String HELP_STRING = ":h";
  private final static String GRAMMAR_STRING = ":g";
  private final static String PROMPTER = "> ";
  private final static String TAB_CHAR = "\t";
  private final static String CONTINUE = "| ";

  private static SymbolTable symbolTable;
  static Scanner in;

  public static void main(String[] args) {
    symbolTable = null;

    in = new Scanner(System.in);

    startUp();
    prompt();
    String line = CommandProcessing.acquireCommand(in.nextLine(), 0);
    while(!line.equals(QUIT_STRING)) {
      if (line.equals(HELP_STRING)) {
        help();
      } else if (line.equals(GRAMMAR_STRING)) {
        try {
          printGrammar();
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else if (!line.isEmpty()) {
        processCommand(line);
      }
      prompt();
      line = CommandProcessing.acquireCommand(in.nextLine(), 0);
    }
  }

  private static void printGrammar() throws IOException {
    System.out.println("\n-- WACC LANGUAGE GRAMMAR --\n");
    System.out.println("The root rule for the shell is \"command\"\n");

    File parser = new File("antlr_config/WACCParser.g4");
    FileReader fileReader = new FileReader(parser);
    BufferedReader bufferedReader = new BufferedReader(fileReader);

    while (bufferedReader.ready()) {
      String line = bufferedReader.readLine();
      line = line.contains("//") ? line.substring(0, line.indexOf("//")) : line;
      if (!line.isEmpty()) System.out.println(line);
      if (line.endsWith(";")) System.out.println();
    }
  }

  private static void help() {
    System.out.println("Type any valid WACC expression, statement or function");
    System.out.println("Type \":g\" to see the grammar for the language");
    System.out.println("Type \":q\" to quit\n");
  }

  private static void startUp() {
    System.out.println("\n-- WELCOME TO THE WACC INTERACTIVE SHELL --\n");
    System.out.println("Type \":h\" for help");
    System.out.println("Type \":q\" to quit\n");
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


  }

  private static void processStatement(StatementNode statement) {
    if (semErrorCheck(statement)) return;
    /* symbol table should now have populated any variables
     * declared in statement */

    statement.applyStatement(symbolTable);
  }

  private static void processRHS(AssignRHS rhs) {
    if (semErrorCheck(rhs)) return;

    Object evaluated = rhs.evaluate(symbolTable);
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

  static void promptWithIndent(int level) {
    StringBuilder builder = new StringBuilder(CONTINUE);

    for (int i = 0; i < level; i++) {
      builder.append(TAB_CHAR);
    }

    System.out.print(builder.toString());
  }
}

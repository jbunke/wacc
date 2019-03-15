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
import shell.cliProcessing.ShellSettings;
import shell.cliProcessing.SpecialCommands;
import shell.structural.Heap;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * WACCShell is the root class of our extension
 * */
public class WACCShell {
  private static final String RUNTIME_ERROR = "Runtime Error:";

  /* The ANSI constants are for standard output formatting */
  public static final String ANSI_RESET = "\u001B[0m";
  private static final String ANSI_BOLD = "\u001B[1m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  private static final String ANSI_PURPLE = "\u001B[35m";

  private final static String QUIT_STRING = ":q";

  private final static String COMMENT = "#";
  private final static String PROMPTER = " > ";
  private final static String TAB_CHAR = "\t";
  private final static String CONTINUE = "| ";

  /**
   * username is displayed every time the shell prompts the user for input.
   *
   * A username USERNAME is assigned with the ":me USERNAME" command
   *
   * username is saved when it is assigned and when the shell closes,
   * and is preserved on start-up
   * @see #saveUsername()
   * @see #loadUsername()
   * */
  public static String username = "user";

  /**
   * settings for the shell currently include:
   *
   * - autocomplete: whether or not the user wants the shell to autocomplete
   * keywords in multi-line statements like "if" and "while"
   *
   * settings is saved when any setting is updated and when the shell closes,
   * and is preserved on start-up
   * */
  public static ShellSettings settings;

  /**
   * symbolTable keeps track of functions and variables that are declared
   * during the shell's runtime
   * */
  public static SymbolTable symbolTable;

  /**
   * heap allocates addresses for pairs and arrays in memory
   * */
  public static Heap heap;

  /**
   * in is the Scanner for the standart input stream (System.in)
   * */
  public static Scanner in;

  public static void main(String[] args) {
    symbolTable = null;
    heap = new Heap();
    settings = new ShellSettings();
    in = new Scanner(System.in);

    settings.loadSettings();
    loadUsername();

    startUp();

    commandCycle(in, false);

    shellExit();

    settings.saveSettings();
    saveUsername();
  }

  /**
   * commandCycle takes a command as input from scanner, processes it, and
   * repeats while the scanner has input. This is the core functionality loop
   * of the shell.
   *
   * @param scanner A Scanner with the InputStream from which to be receiving
   *                input
   *                Because of the ":script FILEPATH.hacc" command, it is
   *                possible to receive input from script files in addition
   *                to System.in
   *
   * @param manualNewline If scanner is not from System.in, nextLine() will not
   *                      be triggered by an ENTER keystroke. Thus, to mimic
   *                      the behaviour of the "in" Scanner execution, the
   *                      flag manualNewLine would be passed as "true"
   * */
  public static void commandCycle(Scanner scanner, boolean manualNewline) {
    do {
      prompt();
      String line = CommandProcessing.
              acquireCommand(scanner.nextLine(),0, scanner);
      if (line.equals(QUIT_STRING)) return;
      if (!line.isEmpty() &&
              !line.startsWith(COMMENT)) {
        if (manualNewline) System.out.println(line);
        if (SpecialCommands.commandMatchCheck(line)) {
          processCommand(line);
        }
      } else if (manualNewline) System.out.println();
    } while (scanner.equals(in) || scanner.hasNext());
  }

  public static void saveUsername() {
    try {
      FileWriter writer = new FileWriter(
              new File("res/username"), false);
      writer.write(username);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Attempts to load the username from "res/username". If the file doesn't
   * exist, creates the file and initialises username to "user"
   * */
  public static void loadUsername()  {
    File file = new File("res/username");

    try {
      if (!file.createNewFile()) {
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        List<String> lines = br.lines().collect(Collectors.toList());
        if (lines.size() > 0) username = lines.get(0).trim();
      } else {
        username = "user";
        saveUsername();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void shellExit() {
    System.out.print(ANSI_GREEN);
    System.out.print("\nThank you for using our interactive shell!");
    System.out.println(ANSI_RESET);
  }

  private static void startUp() {
    System.out.print(ANSI_PURPLE + ANSI_BOLD);
    System.out.println("\n-- WELCOME TO THE WACC INTERACTIVE SHELL --\n");
    System.out.print(ANSI_RESET + ANSI_GREEN);
    System.out.println("Type \":h\" for help");
    System.out.println("Type \"" + QUIT_STRING + "\" to quit");
    System.out.print(ANSI_RESET);
  }

  /**
   * processCommand takes a String command and passes it to the
   * ANTLR-constructed lexer and parser. The parse tree is constructed with
   * the "command" rule as the root. "command" is an extension to the
   * WACCParser.g4 file that can be a rhs, a statement, or a function: the
   * valid WACC inputs into the shell
   *
   * @param line The String to be lexed, parsed, and executed as a command
   * */
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

  /**
   * @see #processCommand(String)
   * processFunction is called from processCommand. It populates the
   * function into the symbolTable
   * @see #symbolTable
   *
   * @param function The FunctionDefinitionNode to be populated
   * */
  private static void processFunction(FunctionDefinitionNode function) {
    if (semErrorCheck(function)) return;

    symbolTable.add(function.getIdentifier(), function);
  }

  /**
   * @see #processCommand(String)
   * processStatement is called from processCommand. It executes statement.
   *
   * @param statement The StatementNode to be executed
   * */
  private static void processStatement(StatementNode statement) {
    if (semErrorCheck(statement)) return;
    /* symbol table should now have populated any variables
     * declared in statement */

    statement.applyStatement(symbolTable, heap);
  }

  /**
   * @see #processCommand(String)
   * processRHS is called from processCommand if line evaluates to an
   * AssignRHS Node after AST construction. It evaluates rhs and
   * prints its result.
   *
   * @param rhs The AssignRHS node to be evaluated
   * */
  private static void processRHS(AssignRHS rhs) {
    if (semErrorCheck(rhs)) return;

    Object evaluated = rhs.evaluate(symbolTable, heap);
    if (isRuntimeError(evaluated)) {
      System.out.print(ANSI_RED);
      System.out.println(evaluated);
      System.out.print(ANSI_RESET);
    } else {
      System.out.println(evaluated);
    }
  }

  /**
   * semErrorCheck is called from the processCommand splinter methods.
   * It checks node for semantic errors and returns true if there are
   * any. When true is returned to the caller, it returns early before
   * executing the node.
   * @see #processFunction(FunctionDefinitionNode)
   * @see #processStatement(StatementNode)
   * @see #processRHS(AssignRHS)
   *
   * @param node The Node to be checked for errors
   *
   * @return Whether Node causes SemanticError(s)
   * */
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
    System.out.print(
            ANSI_BOLD + ANSI_PURPLE + username + PROMPTER + ANSI_RESET);
  }

  /**
   * promptWithIndent formats the input prompt on successive
   * lines of a multi-line statement
   *
   * @param level The indentation level
   * */
  public static void promptWithIndent(int level) {
    StringBuilder builder = new StringBuilder();
    // align continues with right arrow above
    for (int i = 0; i < username.length() + 1; i++) {
      builder.append(" ");
    }

    builder.append(ANSI_BOLD);
    builder.append(CONTINUE);
    for (int i = 0; i < level; i++) {
      builder.append(TAB_CHAR);
    }

    System.out.print(ANSI_PURPLE);
    System.out.print(builder.toString() + ANSI_RESET);
  }

  private static boolean isRuntimeError(Object value) {
    return (value instanceof String)
        && ((String) value).startsWith(RUNTIME_ERROR);
  }
}

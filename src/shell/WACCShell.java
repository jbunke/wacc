package shell;

import antlr.WACCLexer;
import antlr.WACCParser;
import frontend.Visitor;
import frontend.WACCParserErrorListener;
import frontend.abstractSyntaxTree.Node;
import frontend.abstractSyntaxTree.assignment.AssignRHS;
import frontend.abstractSyntaxTree.expressions.ExpressionNode;
import frontend.abstractSyntaxTree.statements.StatementNode;
import frontend.abstractSyntaxTree.typeNodes.FunctionDefinitionNode;
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
  private final static String TAB_CHAR = "\t";
  private final static String CONTINUE = "| ";

  private final static String IF_TOK = "if";
  private final static String THEN_TOK = "then";
  private final static String ELSE_TOK = "else";
  private final static String FI_TOK = "fi";

  private final static String WHILE_TOK = "while";
  private final static String DO_TOK = "do";
  private final static String DONE_TOK = "done";

  private final static String IS_TOK = "is";
  private final static String END_TOK = "end";


  private final static String SEMI_COLON_TOK = ";";

  private static SymbolTable symbolTable;
  private static Scanner in;

  public static void main(String[] args) {
    symbolTable = null;

    in = new Scanner(System.in);

    prompt();
    String line = acquireCommand(in.nextLine(), 0);
    while(!line.equals(QUIT_STRING)) {
      processCommand(line);

      prompt();
      line = acquireCommand(in.nextLine(), 0);
    }
  }

  private static String acquireCommand(String line, int level) {
    String res = line.trim();

    if (line.startsWith(IF_TOK + " ") && line.endsWith(" " + THEN_TOK)) {
      res = acquireIfCommand(line, level + 1);
    } else if (line.startsWith(WHILE_TOK + " ") && line.endsWith(" " + DO_TOK)) {
      res = acquireWhileCommand(line, level + 1);
    } else if (line.endsWith(" " + IS_TOK)) {
      res = acquireFunctionCommand(line, level  + 1);
    }

    return res;
  }

  private static String acquireFunctionCommand(String startLine, int level) {
    StringBuilder commandBuilder = new StringBuilder(startLine);
    String line;
    boolean end = false;

    do {
      promptWithIndent(level);
      line = acquireCommand(in.nextLine(), level);
      commandBuilder.append(" ");
      commandBuilder.append(line);

      if (!line.endsWith(SEMI_COLON_TOK)) {
        end = true;
        commandBuilder.append(" ");
        commandBuilder.append(END_TOK);
        promptWithIndent(level - 1);
        System.out.print(END_TOK);
      }
    } while (!end);

    line = in.nextLine();
    if (line.equals(SEMI_COLON_TOK)) {
      commandBuilder.append(line);
    }

    return commandBuilder.toString();
  }

  private static String acquireWhileCommand(String startLine, int level) {
    StringBuilder commandBuilder = new StringBuilder(startLine);
    String line;
    boolean done = false;

    do {
      promptWithIndent(level);
      line = acquireCommand(in.nextLine(), level);
      commandBuilder.append(" ");
      commandBuilder.append(line);

      if (!line.endsWith(SEMI_COLON_TOK)) {
        done = true;
        commandBuilder.append(" ");
        commandBuilder.append(DONE_TOK);
        promptWithIndent(level - 1);
        System.out.print(DONE_TOK);
      }
    } while (!done);

    line = in.nextLine();
    if (line.equals(SEMI_COLON_TOK)) {
      commandBuilder.append(line);
    }

    return commandBuilder.toString();
  }


  private static String acquireIfCommand(String startLine, int level) {
    StringBuilder commandBuilder = new StringBuilder(startLine);
    String line;
    boolean elseIncluded = false;
    boolean fiIncluded = false;

    do {
      promptWithIndent(level);
      line = acquireCommand(in.nextLine(), level);
      commandBuilder.append(" ");
      commandBuilder.append(line);

      if (elseIncluded && !fiIncluded && !line.endsWith(SEMI_COLON_TOK)) {
        fiIncluded = true;
        commandBuilder.append(" ");
        commandBuilder.append(FI_TOK);
        promptWithIndent(level - 1);
        System.out.print(FI_TOK);
      }

      if (!elseIncluded && !line.endsWith(SEMI_COLON_TOK)) {
        elseIncluded = true;
        commandBuilder.append(" ");
        commandBuilder.append(ELSE_TOK);
        promptWithIndent(level - 1);
        System.out.println(ELSE_TOK);
      }

    } while (!fiIncluded);

    line = in.nextLine();
    if (line.equals(SEMI_COLON_TOK)) {
      commandBuilder.append(line);
    }

    return commandBuilder.toString();
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

  private static void promptWithIndent(int level) {
    StringBuilder builder = new StringBuilder(CONTINUE);

    for (int i = 0; i < level; i++) {
      builder.append(TAB_CHAR);
    }

    System.out.print(builder.toString());
  }
}

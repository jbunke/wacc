package shell.cliProcessing;

import antlr.WACCLexer;
import antlr.WACCParser;
import frontend.Visitor;
import frontend.WACCParserErrorListener;
import frontend.abstractSyntaxTree.ProgramNode;
import frontend.abstractSyntaxTree.typeNodes.FunctionDefinitionNode;
import frontend.symbolTable.SymbolCategory;
import frontend.symbolTable.Variable;
import frontend.symbolTable.types.Type;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import static shell.WACCShell.*;

public class SpecialCommands {

  private final static String SPECIAL_COMMAND_STARTER = ":";

  private final static String HELP_STRING = ":h";
  private final static String GRAMMAR_STRING = ":g";
  private final static String INFO_STRING = ":i";
  private final static String VARIABLES_STRING = ":v";
  private final static String FUNCTIONS_STRING = ":f";
  private final static String RUN_FILE_STRING = ":run";
  private final static String RESET_STRING = ":reset";
  private final static String SET_STRING = ":set";
  private final static String SCRIPT_STRING = ":script";
  private final static String ME_STRING = ":me";

  private final static String SCRIPT_FILE_EXTENSION = ".hacc";

  public static boolean commandMatchCheck(String line) {
    switch (line) {
      case HELP_STRING:
        help();
        return false;
      case GRAMMAR_STRING:
        try {
          printGrammar();
        } catch (IOException e) {
          System.out.print(ANSI_RED);
          System.out.println("Unable to fetch grammar" + ANSI_RESET);
        }
        return false;
      case INFO_STRING:
        info();
        return false;
      case VARIABLES_STRING:
        variables();
        return false;
      case ME_STRING:
        System.out.println(ANSI_GREEN + "Username: " +
                username + ANSI_RESET);
        return false;
      case FUNCTIONS_STRING:
        functions();
        return false;
      case RESET_STRING:
        reset();
        return false;
      default:
        if (line.startsWith(RUN_FILE_STRING + " ")) {
          String filepath = line.substring(line.indexOf(" ") + 1);
          try {
            runFile(filepath);
          } catch (IOException e) {
            System.out.print(ANSI_RED);
            System.out.println("Unable to find file at \"" +
                    filepath + "\"" + ANSI_RESET);
          }
          return false;
        } else if (line.startsWith(SCRIPT_STRING + " ")) {
          String filepath = line.substring(line.indexOf(" ") + 1);
          try {
            runScript(filepath);
          } catch (IOException e) {
            System.out.print(ANSI_RED);
            System.out.println("Unable to find file at \"" +
                    filepath + "\"" + ANSI_RESET);
          }
          return false;
        } else if (line.startsWith(ME_STRING + " ")) {
          username = line.substring(line.indexOf(" ") + 1);
          saveUsername();
          return false;
        } else if (line.startsWith(SET_STRING + " ")) {
          updateSetting(line);
          return false;
        } else if (line.startsWith(SPECIAL_COMMAND_STARTER)) {
          invalidCommand(line);
          return false;
        }
        return true;
    }
  }

  private static void updateSetting(String line) {
    String setting = line.substring(line.indexOf(" ") + 1);
    setting = setting.contains(" ") ?
            setting.substring(0, setting.indexOf(" ")) : setting;

    // triggers if the VALUE field is left off the command
    if (line.indexOf(" ") == line.lastIndexOf(" ")) {
      boolean settingState;
      switch (setting) {
        case "autocomplete":
          settingState = settings.isAutocomplete();
          break;
        default:
          System.out.println(ANSI_RED + "No settings matched \""
                  + setting + "\"" + ANSI_RESET);
          return;
      }

      System.out.println(ANSI_GREEN + "Setting \"" + setting +
              "\" is set to " + settingState + ANSI_RESET);
      return;
    }

    // otherwise command is a setting assignment
    String value = line.substring(line.lastIndexOf(" ") + 1);
    boolean toSet = Boolean.parseBoolean(value);

    switch (setting) {
      case "autocomplete":
        settings.setAutocomplete(toSet);
        break;
      default:
        System.out.println(ANSI_RED + "No settings matched \""
                + setting + "\"" + ANSI_RESET);
        return;
    }

    System.out.println(ANSI_GREEN + "Set setting \"" + setting +
            "\" to " + toSet + ANSI_RESET);
    settings.saveSettings();

  }

  private static void invalidCommand(String command) {
    System.out.print(ANSI_RED);
    System.out.println(command + " is not a valid command");
    System.out.println("Type \"" + HELP_STRING +
            "\" for a list of valid commands");
    System.out.print(ANSI_RESET);
  }

  private static void reset() {
    symbolTable = null;
    heap.reset();
    username = "user";
    saveUsername();

    System.out.print(ANSI_GREEN);
    System.out.println("Shell has been reset");
    System.out.print(ANSI_RESET);
  }

  private static void runScript(String filepath) throws IOException {
    FileInputStream stream = new FileInputStream(new File(filepath));

    int lastSlash = Math.max(filepath.lastIndexOf("\\") + 1,
            filepath.lastIndexOf("/") + 1);
    String filename = filepath.substring(lastSlash).
            replaceAll(SCRIPT_FILE_EXTENSION, "");

    System.out.print(ANSI_GREEN);
    System.out.println("Executing script: " + filename);
    System.out.print(ANSI_RESET);

    username = filename;

    commandCycle(new Scanner(stream), true);

    loadUsername();

    System.out.print(ANSI_GREEN);
    System.out.println("Finished executing script: " + filename);
    System.out.print(ANSI_RESET);
  }

  private static void runFile(String filepath) throws IOException {
    CharStream input = CharStreams.fromFileName(filepath);
    WACCLexer lexer = new WACCLexer(input);
    lexer.removeErrorListeners();
    TokenStream tokens = new CommonTokenStream(lexer);
    WACCParser parser = new WACCParser(tokens);
    parser.removeErrorListeners();

    WACCParserErrorListener synErrors =
        new WACCParserErrorListener();
    parser.addErrorListener(synErrors);

    WACCParser.ProgContext parseTree = parser.prog();

    if (synErrors.hasError()) {
      for (String error : synErrors.getSyntaxErrors()) {
        System.out.print(ANSI_RED);
        System.out.println(error);
        System.out.print(ANSI_RESET);
      }
      return;
    }

    Visitor visitor = new Visitor();
    ProgramNode program = (ProgramNode) visitor.visit(parseTree);

    if (semErrorCheck(program)) {
      return;
    }

    program.execute(symbolTable, heap);
  }

  private static void functions() {
    System.out.print(ANSI_GREEN);
    if (symbolTable == null ||
        symbolTable.getEntries().size() == 0) {
      System.out.println("No functions in scope");
      System.out.print(ANSI_RESET);
      return;
    }

    System.out.println("\nFunctions in scope:\n");

    List<Map.Entry<String, SymbolCategory>> entries =
        symbolTable.getEntries();
    entries = entries.parallelStream().
        filter(x -> x.getValue() instanceof FunctionDefinitionNode).
        collect(Collectors.toList());
    for (Map.Entry<String, SymbolCategory> entry : entries) {
      FunctionDefinitionNode function =
          (FunctionDefinitionNode) entry.getValue();

      System.out.print(function.getIdentifier() + "(");

      List<Type> paramTypes = function.getParameterList().getParamTypes();
      for (int i = 0; i < paramTypes.size(); i++) {
        System.out.print(paramTypes.get(i).toString());
        if (i < paramTypes.size() - 1) {
          System.out.print(", ");
        }
      }

      System.out.println(") -> " + function.getReturnType().toString());
    }
    System.out.println();
    System.out.print(ANSI_RESET);
  }

  private static void variables() {
    System.out.print(ANSI_GREEN);
    if (symbolTable == null ||
        symbolTable.getEntries().size() == 0) {
      System.out.println("No variables in scope");
      System.out.print(ANSI_RESET);
      return;
    }

    System.out.println("\nVariables in scope:\n");

    List<Map.Entry<String, SymbolCategory>> entries =
        symbolTable.getEntries();
    entries = entries.parallelStream().
        filter(x -> x.getValue() instanceof Variable).
        collect(Collectors.toList());
    for (Map.Entry<String, SymbolCategory> entry : entries) {
      System.out.println(entry.getKey() + " -> " +
          format(((Variable) entry.getValue()).getValue()));
    }
    System.out.println();
    System.out.print(ANSI_RESET);
  }

  private static String format(Object object) {
    if (object instanceof String) {
      return "\"" + object + "\"";
    } else if (object instanceof Character) {
      return "'" + object + "'";
    }
    return object.toString();
  }

  private static void info() {
    System.out.print(ANSI_GREEN);
    System.out.println("WACC Compiler & Interactive Shell developed for " +
        "second-year Compilers project at\n" +
        "Imperial College London (Department of Computing) from " +
        "January - March 2019\n");
    System.out.println("Authors:\nJordan Bunke (jtb17@ic.ac.uk)\n" +
        "Patrick Henderson (pah17@ic.ac.uk)\n" +
        "Buneme Kyakilika (bk317@ic.ac.uk)\n" +
        "Kapilan Manu Neethi Cholan (km2717@ic.ac.uk)");
    System.out.print(ANSI_RESET);
  }

  private static void help() {
    System.out.print(ANSI_GREEN);
    System.out.println("Type any valid WACC expression, statement or function");

    System.out.println();
    System.out.println("Type \"" + FUNCTIONS_STRING +
        "\" for functions in scope");

    System.out.println();
    System.out.println("Type \"" + GRAMMAR_STRING +
        "\" to see the grammar for the language");

    System.out.println();
    System.out.println("Type \"" + INFO_STRING +
        "\" for project information");

    System.out.println();
    System.out.println("Type \"" + ME_STRING + "\" to see username and \"" +
            ME_STRING + " USERNAME\" to set a new username");

    System.out.println();
    System.out.println("Type \":q\" to quit");

    System.out.println();
    System.out.println("Type \"" + RESET_STRING + "\" to reset the shell");

    System.out.println();
    System.out.println("Type \"" + RUN_FILE_STRING + " FILEPATH.wacc\"" +
        " to execute a WACC file");

    System.out.println();
    System.out.println("Type \"" + SCRIPT_STRING + " FILEPATH" +
            SCRIPT_FILE_EXTENSION + "\"" +
            " to execute a script for the shell");

    System.out.println();
    System.out.println("Type \"" + SET_STRING + " SETTING BOOL-LITERAL\"" +
            " to update a setting");
    System.out.println("Type \"" + SET_STRING + " SETTING\"" +
            " to check the status of a setting\nSettings: ");
    System.out.println("\t\tautocomplete: multi-line bodies and statements" +
            " like \"if\" and \"while\" have autocompletion");

    System.out.println();
    System.out.println("Type \"" + VARIABLES_STRING +
        "\" for variables in scope");
    System.out.print(ANSI_RESET);
  }

  private static void printGrammar() throws IOException {
    System.out.print(ANSI_GREEN);
    System.out.println("\n-- WACC LANGUAGE GRAMMAR --\n");
    System.out.println("The root rule for the shell is \"command\"\n");

    File parser = new File("antlr_config/WACCParser.g4");
    FileReader fileReader = new FileReader(parser);
    BufferedReader bufferedReader = new BufferedReader(fileReader);

    while (bufferedReader.ready()) {
      String line = bufferedReader.readLine();
      line = line.contains("//") ? line.substring(0, line.indexOf("//")) : line;
      if (!line.isEmpty()) {
        System.out.println(line);
      }
      if (line.endsWith(";")) {
        System.out.println();
      }
    }
    System.out.print(ANSI_RESET);
  }
}

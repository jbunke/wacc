package shell.cliProcessing;

import frontend.abstractSyntaxTree.typeNodes.FunctionDefinitionNode;
import frontend.symbolTable.SymbolCategory;
import frontend.symbolTable.Variable;
import frontend.symbolTable.types.Type;
import shell.WACCShell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SpecialCommands {

  private final static String HELP_STRING = ":h";
  private final static String GRAMMAR_STRING = ":g";
  private final static String INFO_STRING = ":i";
  private final static String VARIABLES_STRING = ":v";
  private final static String FUNCTIONS_STRING = ":f";
  private final static String RUN_FILE_STRING = ":run";

  public static boolean commandMatchCheck(String line) {
    switch (line) {
      case HELP_STRING:
        help();
        return false;
      case GRAMMAR_STRING:
        try {
          printGrammar();
        } catch (IOException e) {
          e.printStackTrace();
        }
        return false;
      case INFO_STRING:
        info();
        return false;
      case VARIABLES_STRING:
        variables();
        return false;
      case FUNCTIONS_STRING:
        functions();
        return false;
      default:
        if (line.startsWith(RUN_FILE_STRING)) {
          
        }
        return true;
    }
  }

  private static void functions() {
    if (WACCShell.symbolTable == null ||
            WACCShell.symbolTable.getEntries().size() == 0) {
      System.out.println("No functions in scope\n");
      return;
    }

    System.out.println("Functions in scope:\n");

    List<Map.Entry<String, SymbolCategory>> entries =
            WACCShell.symbolTable.getEntries();
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
        if (i < paramTypes.size() - 1) System.out.print(", ");
      }

      System.out.println(") -> " + function.getReturnType().toString());
    }
    System.out.println();
  }

  private static void variables() {
    if (WACCShell.symbolTable == null ||
            WACCShell.symbolTable.getEntries().size() == 0) {
      System.out.println("No variables in scope\n");
      return;
    }

    System.out.println("Variables in scope:\n");

    List<Map.Entry<String, SymbolCategory>> entries =
            WACCShell.symbolTable.getEntries();
    entries = entries.parallelStream().
            filter(x -> x.getValue() instanceof Variable).
            collect(Collectors.toList());
    for (Map.Entry<String, SymbolCategory> entry : entries) {
      System.out.println(entry.getKey() + " -> " +
              format(((Variable) entry.getValue()).getValue()));
    }
    System.out.println();
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
    System.out.println("WACC Compiler & Interactive Shell developed for " +
            "second-year Compilers project at\n" +
            "Imperial College London (Department of Computing) from " +
            "January - March 2019\n");
    System.out.println("Authors:\nJordan Bunke (jtb17@ic.ac.uk), " +
            "Patrick Henderson (pah17@ic.ac.uk),\nBuneme Kyakilika (bk317@ic.ac.uk), " +
            "Kapilan Manu Neethi Cholan (km2717@ic.ac.uk)\n");
  }

  private static void help() {
    System.out.println("Type any valid WACC expression, statement or function");
    System.out.println("Type \"" + FUNCTIONS_STRING +
            "\" for functions in scope");
    System.out.println("Type \"" + GRAMMAR_STRING +
            "\" to see the grammar for the language");
    System.out.println("Type \"" + INFO_STRING +
            "\" for project information");
    System.out.println("Type \":q\" to quit\n");
    System.out.println("Type \"" + VARIABLES_STRING +
            "\" for variables in scope");
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
}

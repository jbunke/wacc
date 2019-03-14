package shell.cliProcessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SpecialCommands {

  private final static String HELP_STRING = ":h";
  private final static String GRAMMAR_STRING = ":g";
  private final static String INFO_STRING = ":i";

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
      default:
        return true;
    }
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
    System.out.println("Type \"" + GRAMMAR_STRING +
            "\" to see the grammar for the language");
    System.out.println("Type \"" + INFO_STRING +
            "\" for project information");
    System.out.println("Type \":q\" to quit\n");
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

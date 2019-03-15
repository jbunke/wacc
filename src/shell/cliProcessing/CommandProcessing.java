package shell.cliProcessing;

import shell.WACCShell;

import java.util.function.Consumer;

public class CommandProcessing {
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

  public static String acquireCommand(String line, int level) {
    String res = line.trim();

    if (res.startsWith(IF_TOK + " ") && line.endsWith(" " + THEN_TOK)) {
      res = acquireIfCommand(line, level + 1);
    } else if (res.startsWith(WHILE_TOK + " ") && line.endsWith(" " + DO_TOK)) {
      res = acquireWhileCommand(line, level + 1);
    } else if (res.endsWith(" " + IS_TOK)) {
      res = acquireFunctionCommand(line, level  + 1);
    }

    return res;
  }

  private static void generalisedAutocomplete(StringBuilder sb, String token,
                                              Consumer<String> function,
                                              int level) {
    sb.append(" ");
    sb.append(token);
    WACCShell.promptWithIndent(level - 1);
    function.accept(token);
  }

  private static String acquireFunctionCommand(String startLine, int level) {
    StringBuilder commandBuilder = new StringBuilder(startLine);
    String line;
    boolean end = false;

    do {
      WACCShell.promptWithIndent(level);
      line = acquireCommand(WACCShell.in.nextLine(), level);
      commandBuilder.append(" ");
      commandBuilder.append(line);

      if (!line.endsWith(SEMI_COLON_TOK)) {
        end = true;
        generalisedAutocomplete(commandBuilder, END_TOK,
                System.out::println, level);
      }
    } while (!end);

    return commandBuilder.toString();
  }

  private static String acquireWhileCommand(String startLine, int level) {
    StringBuilder commandBuilder = new StringBuilder(startLine);
    String line;
    boolean done = false;

    do {
      WACCShell.promptWithIndent(level);
      line = acquireCommand(WACCShell.in.nextLine(), level);
      commandBuilder.append(" ");
      commandBuilder.append(line);

      if (!line.endsWith(SEMI_COLON_TOK)) {
        done = true;
        generalisedAutocomplete(commandBuilder, DONE_TOK,
                System.out::print, level);
      }
    } while (!done);

    line = WACCShell.in.nextLine();
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
      WACCShell.promptWithIndent(level);
      line = acquireCommand(WACCShell.in.nextLine(), level);
      commandBuilder.append(" ");
      commandBuilder.append(line);

      if (elseIncluded && !fiIncluded && !line.endsWith(SEMI_COLON_TOK)) {
        fiIncluded = true;
        generalisedAutocomplete(commandBuilder, FI_TOK,
                System.out::print, level);
      }

      if (!elseIncluded && !line.endsWith(SEMI_COLON_TOK)) {
        elseIncluded = true;
        generalisedAutocomplete(commandBuilder, ELSE_TOK,
                System.out::println, level);
      }

    } while (!fiIncluded);

    line = WACCShell.in.nextLine();
    if (line.equals(SEMI_COLON_TOK)) {
      commandBuilder.append(line);
    }

    return commandBuilder.toString();
  }
}

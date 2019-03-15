package shell.cliProcessing;

import java.util.Scanner;
import java.util.function.Consumer;

import static shell.WACCShell.*;

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

  public static String acquireCommand(String line, int level, Scanner scanner) {
    String res = line.trim();

    if (res.startsWith(IF_TOK + " ") && line.endsWith(" " + THEN_TOK)) {
      res = acquireIfCommand(line, level + 1, scanner);
    } else if (res.startsWith(WHILE_TOK + " ") && line.endsWith(" " + DO_TOK)) {
      res = acquireWhileCommand(line, level + 1, scanner);
    } else if (res.endsWith(" " + IS_TOK)) {
      res = acquireFunctionCommand(line, level  + 1, scanner);
    }

    return res;
  }

  private static void generalisedAutocomplete(StringBuilder sb, String token,
                                              Consumer<String> function,
                                              int level) {
    promptWithIndent(level - 1);
    sb.append(" ");
    sb.append(token);
    function.accept(token);
  }

  private static String generalisedLineFetch(StringBuilder sb,
                                             int level, Scanner scanner) {
    promptWithIndent(level);
    String line = acquireCommand(scanner.nextLine(), level, scanner);
    sb.append(" ");
    sb.append(line);
    return line;
  }

  private static String generalisedMatcher(boolean increment, int level,
                                           Consumer<String> function, String line,
                                           String token, StringBuilder sb) {
    boolean var = false;
    if (!settings.isAutocomplete()) {
      if (line.endsWith(token)) {
        var = true;
        if (increment) level++;
      } else if (!line.endsWith(SEMI_COLON_TOK)) {
        level--;
      }
    } else {
      if (!line.endsWith(SEMI_COLON_TOK)) {
        var = true;
        generalisedAutocomplete(sb, token,
                function, level);
      }
    }

    return level + " " + var;
  }

  private static String acquireFunctionCommand(String startLine, int level,
                                               Scanner scanner) {
    StringBuilder commandBuilder = new StringBuilder(startLine);
    String line;
    boolean end;

    do {
      line = generalisedLineFetch(commandBuilder, level, scanner);

      String result = generalisedMatcher(false, level,
              System.out::println, line, END_TOK, commandBuilder);
      level = Integer.parseInt(result.substring(0, result.indexOf(" ")));
      end = Boolean.parseBoolean(
              result.substring(result.indexOf(" ") + 1));
    } while (!end);

    return commandBuilder.toString();
  }

  private static String acquireWhileCommand(String startLine, int level,
                                            Scanner scanner) {
    StringBuilder commandBuilder = new StringBuilder(startLine);
    String line;
    boolean done;

    do {
      line = generalisedLineFetch(commandBuilder, level, scanner);

      String result = generalisedMatcher(false, level,
              System.out::print, line, DONE_TOK, commandBuilder);
      level = Integer.parseInt(result.substring(0, result.indexOf(" ")));
      done = Boolean.parseBoolean(
              result.substring(result.indexOf(" ") + 1));
    } while (!done);

    if (settings.isAutocomplete()) {
      line = scanner.nextLine().trim();
      if (line.equals(SEMI_COLON_TOK)) {
        commandBuilder.append(line);
      }
    }

    return commandBuilder.toString();
  }

  private static String acquireIfCommand(String startLine, int level,
                                         Scanner scanner) {
    StringBuilder commandBuilder = new StringBuilder(startLine);
    String line;
    boolean reachedElse = false;
    boolean reachedFi = false;

    do {
      line = generalisedLineFetch(commandBuilder, level, scanner);

      if (!reachedElse) {
        String result = generalisedMatcher(true, level,
                System.out::println, line, ELSE_TOK, commandBuilder);
        level = Integer.parseInt(result.substring(0, result.indexOf(" ")));
        reachedElse = Boolean.parseBoolean(
                result.substring(result.indexOf(" ") + 1));
      } else {
        if (!settings.isAutocomplete()) {
          if (line.endsWith(FI_TOK)) {
            reachedFi = true;
          } else if (!line.endsWith(SEMI_COLON_TOK)) {
            level--;
          } else if (line.endsWith(FI_TOK + SEMI_COLON_TOK) ||
                  line.endsWith(FI_TOK + " " + SEMI_COLON_TOK)) {
            reachedFi = true;
          }
        } else {
          if (!line.endsWith(SEMI_COLON_TOK)) {
            reachedFi = true;
            generalisedAutocomplete(commandBuilder, FI_TOK,
                    System.out::print, level);
          }
        }
      }
    } while (!reachedFi);

    if (settings.isAutocomplete()) {
      line = scanner.nextLine().trim();
      if (line.equals(SEMI_COLON_TOK)) {
        commandBuilder.append(line);
      }
    }

    return commandBuilder.toString();
  }
}

package shell;

public class ShellStatementControl {
  public final boolean toExit;
  public final Object value;

  private ShellStatementControl(boolean toExit) {
    this.toExit = toExit;
    value = null;
  }

  private ShellStatementControl(Object value) {
    toExit = true;
    this.value = value;
  }

  public static ShellStatementControl cont() {
    return new ShellStatementControl(false);
  }

  public static ShellStatementControl exit() {
    return new ShellStatementControl(true);
  }

  public static ShellStatementControl returnValue(Object value) {
    return new ShellStatementControl(value);
  }
}

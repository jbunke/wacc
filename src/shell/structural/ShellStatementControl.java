package shell.structural;

/**
 * ShellStatementControl is used as the return type of applyStatement to
 * aid statement execution control flow. Most statement executions do not
 * return anything, but this becomes necessary inside functions.
 *
 * Example:
 * If a function body is a StatementList that contains a ReturnStatementNode
 * in the middle that is triggered, the StatementNodes in the StatementList
 * after the ReturnStatementNode should not execute. Additionally, the return
 * value has to be stored and returned up through the callers.
 * */
public class ShellStatementControl {
  /**
   * A flag used for caller to determine whether to keep executing statements
   * */
  public final boolean toExit;

  /**
   * The return value, if one exists. Only non-null when
   * constructed from returnValue
   * @see #returnValue(Object)
   * @see #ShellStatementControl(Object)
   * */
  public final Object value;

  /**
   * Constructor used by cont and exit, where only the toExit flag matters
   * since there is no return value
   * @see #cont()
   * @see #exit()
   * */
  private ShellStatementControl(boolean toExit) {
    this.toExit = toExit;
    value = null;
  }

  /**
   * Constructor used by returnValue
   * @see #returnValue(Object)
   * */
  private ShellStatementControl(Object value) {
    toExit = true;
    this.value = value;
  }

  /**
   * Called when the statement has no return and execution should continue
   * */
  public static ShellStatementControl cont() {
    return new ShellStatementControl(false);
  }

  /**
   * Called by ExitStatementNode
   * */
  public static ShellStatementControl exit() {
    return new ShellStatementControl(true);
  }

  /**
   * Called by ReturnStatementNode
   *
   * @param value ReturnStatementNode evaluates its return expression
   *              and passes it as "value"
   * */
  public static ShellStatementControl returnValue(Object value) {
    return new ShellStatementControl(value);
  }
}

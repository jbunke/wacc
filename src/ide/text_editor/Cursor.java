package ide.text_editor;

public class Cursor {
  private int line;
  private int column;

  public Cursor() {
    line = 0;
    column = 0;
  }

  public Cursor(int line, int column) {
    this.line = line;
    this.column = column;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public void increment(int inc) {
    line += inc;
  }
}

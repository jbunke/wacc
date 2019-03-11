package ide.text_editor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditorContext {
  private static final String UNTITLED_FILE_PATH = "<untitled>";

  private String filepath;
  private List<String> lines;
  private Cursor cursor;

  public EditorContext() {
    filepath = UNTITLED_FILE_PATH;
    initialiseLinesNewFile();
    cursor = new Cursor();
  }

  public EditorContext(String filepath) {
    this();
    this.filepath = filepath;
    try {
      initialiseLinesFromFile(filepath);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void initialiseLinesNewFile() {
    lines = new ArrayList<>();
    lines.add("");
  }

  private void initialiseLinesFromFile(String filepath) throws IOException {
    File file = new File(filepath);

    FileReader fileReader = new FileReader(file);
    BufferedReader bufferedReader = new BufferedReader(fileReader);

    lines = new ArrayList<>();
    bufferedReader.lines().forEach(lines::add);
  }

  public void typeString(String toAdd) {
    String line = lines.get(cursor.getLine());
    lines.set(cursor.getLine(), line.substring(0,
            cursor.getColumn()) + toAdd + line.substring(cursor.getColumn()));
    cursor.increment(toAdd.length());
  }
}

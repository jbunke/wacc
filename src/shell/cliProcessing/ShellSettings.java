package shell.cliProcessing;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class ShellSettings {
  private final static int SETTINGS_COUNT = 1;

  private boolean autocomplete;

  public ShellSettings() {
    autocomplete = true;
  }

  public boolean isAutocomplete() {
    return autocomplete;
  }

  public void setAutocomplete(boolean autocomplete) {
    this.autocomplete = autocomplete;
  }

  public void saveSettings() {
    try {
      FileWriter writer = new FileWriter(
              new File("res/settings"), false);
      writer.write(Boolean.toString(autocomplete) + "\n");
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void loadSettings()  {
    File file = new File("res/settings");

    try {
      if (!file.createNewFile()) {
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        List<String> lines = br.lines().collect(Collectors.toList());
        if (lines.size() >= SETTINGS_COUNT) {
          autocomplete = Boolean.parseBoolean(lines.get(0).trim());
        }
      } else {
        saveSettings();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

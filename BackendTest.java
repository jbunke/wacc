import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.Runtime;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class BackendTest {

  private static final int FILENAME_ARG_INDEX = 0;
  private static final int EXPECTED_NUM_ARGS = 1;
  private static final int TEST_FAILURE_EXIT = 1;
  private static final int TEST_SUCCESS_EXIT = 0;

  public static void main(String[] args) {
    if (args.length != EXPECTED_NUM_ARGS) {
      System.out.println("Expected number of args: " + EXPECTED_NUM_ARGS + "\n"
          + "Number received: " + args.length);
      System.exit(TEST_FAILURE_EXIT);
    }

    String filename = args[FILENAME_ARG_INDEX];

    // Get the output from the assembly file our compiler generated
    String emulatorOut = getEmulatorOutput(filename);

    // Get the output from the reference compiler
    String refCompilerOut = getRefCompilerOutput(filename);

    if (!emulatorOut.equals(refCompilerOut)) {
      System.out.println("\nMismatched output between reference compiler and "
          + "your wacc compiler for\n >>>" + filename);

      System.out.println("\n");

      System.out.println("Your compiler output:\n");
      System.out.println("++++++++++++++++++++++++++++++");
      System.out.println(emulatorOut);
      System.out.println("++++++++++++++++++++++++++++++\n");

      System.out.println("\n\n");

      System.out.println("Reference compiler output:\n");
      System.out.println("++++++++++++++++++++++++++++++");
      System.out.println(refCompilerOut);
      System.out.println("++++++++++++++++++++++++++++++\n");

      System.exit(TEST_FAILURE_EXIT);
    }

    System.exit(TEST_SUCCESS_EXIT);
  }

  private static String getRefCompilerOutput(String filename) {

    String outputFile = filename.replaceFirst(".*/(\\w+).*", "$1") + ".out";
    StringBuilder outputString = new StringBuilder();
    {
      File file = new File(outputFile);
      BufferedReader br = null;
      try {
        br = new BufferedReader(new FileReader(file));
        String line;
        boolean foundExit = false;
        boolean outputFoundStart = false;
        boolean outputFoundEnd = false;
        while ((line = br.readLine()) != null && !foundExit) {
          if (line.equals(
              "===========================================================")) {
            if (!outputFoundStart) {
              outputFoundStart = true;
            } else {
              outputFoundEnd = true;
              outputFoundStart = false;
            }
          } else if (outputFoundStart) {
            outputString.append(System.getProperty("line.separator"));
            outputString.append(line);
          } else if (outputFoundEnd) {
            outputString.append(System.getProperty("line.separator"));
            outputString.append(line);
            foundExit = true;
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return outputString.toString();
  }

  private static String getEmulatorOutput(String filename) {
    StringBuilder outputString = new StringBuilder();
    String assemblyFile = filename.replaceFirst(".*/(\\w+).*", "$1");
    try {
      Process process = Runtime.getRuntime().exec("bash tests/emulate " + assemblyFile);
      process.waitFor();

      BufferedReader reader =
          new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;
      boolean outputFound = false;
      while ((line = reader.readLine()) != null) {
        outputString.append(System.getProperty("line.separator"));
        outputString.append(line);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return outputString.toString();
  }
}
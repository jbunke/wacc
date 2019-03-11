package ide;

public class IDEMain {
  public static void main(String[] args) {
    if (args.length == 1) {
      IDE.runWithFile(args[0]);
    } else {
      IDE.runWithoutFile();
    }
  }
}

import java.io.FileInputStream;
import java.io.IOException;

public class WACCCompiler {

    public static void main(String[] args) {
        // Test arguments are correct
        if (args.length == 0) {
            System.out.println("No File was specified");
            System.exit(1);
        }
        if (args.length > 1) {
            System.out.println("Too many arguments given");
            System.exit(1);
        }

        String file = args[0];

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            // TODO Actual Lexing and Parsing
        } catch (IOException e) {
            System.err.println("Incorrect filepath name supplied");
        }
    }
}

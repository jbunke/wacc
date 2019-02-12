import antlr.WACCLexer;
import antlr.WACCParser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

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
            ANTLRInputStream input = new ANTLRInputStream(fileInputStream);
            WACCLexer lexer = new WACCLexer(input);
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            WACCParser parser = new WACCParser(tokenStream);

            //ExampleVisitor visitor = new ExampleVisitor();
            WACCParser.ProgContext parseTree = parser.prog();
            //visitor.visit(parseTree);
        } catch (IOException e) {
            System.err.println("File at path (" + file + ") doesn't exist");
        }
    }
}

import antlr.WACCLexer;
import antlr.WACCParser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class WACCCompiler {

    private static final int EXPECTED_NUM_ARGS = 1;
    private static final int INCORRECT_ARGS_EXIT = 1;

    public static void main(String[] args) {
        // Test arguments are correct

        if (args.length != EXPECTED_NUM_ARGS) {
            System.out.println(args.length + " arguments given, " +
                               EXPECTED_NUM_ARGS + " expected.");
            System.exit(INCORRECT_ARGS_EXIT);
        }

        String file = args[0];

        try {
            CharStream input = CharStreams.fromFileName(file);
            WACCLexer lexer = new WACCLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            WACCParser parser = new WACCParser(tokens);

            //ExampleVisitor visitor = new ExampleVisitor();
            WACCParser.ProgContext parseTree = parser.prog();
            //visitor.visit(parseTree);
        } catch (IOException e) {
            System.err.println("File at path (" + file + ") doesn't exist");
        }
    }
}

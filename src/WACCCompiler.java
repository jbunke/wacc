import antlr.RecognitionException;
import antlr.WACCLexer;
import antlr.WACCParser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.IOException;

public class WACCCompiler {

    private static final int EXPECTED_NUM_ARGS = 1;

    private static final int INCORRECT_ARGS_EXIT = 1;
    private static final int SYNTAX_ERROR_EXIT = 100;

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
            lexer.removeErrorListeners();
            TokenStream tokens = new CommonTokenStream(lexer);
            WACCParser parser = new WACCParser(tokens);
            parser.removeErrorListeners();

            WACCParserErrorListener SyntaxErrorListener = new WACCParserErrorListener();
            parser.addErrorListener(SyntaxErrorListener);

            WACCParser.ProgContext parseTree = parser.prog();

            if (SyntaxErrorListener.hasError()) {
                for (String error : SyntaxErrorListener.getSyntaxErrors()) {
                    System.out.println(error);
                }
                System.exit(SYNTAX_ERROR_EXIT);
            }

        } catch (IOException e) {
            System.err.println("File at path (" + file + ") doesn't exist");
        } catch (RecognitionException e) {
            System.err.println("Error with prediction, predicate failure or mismatched input occurred.");
        }

    }
}

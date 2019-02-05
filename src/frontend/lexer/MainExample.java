package frontend.lexer;

import antlr.WACCLexer;
import antlr.WACCParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class MainExample {

    public static void main(String[] args) throws IOException {
        CharStream input = new ANTLRInputStream(System.in);

        WACCLexer lexer = new WACCLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        WACCParser parser = new WACCParser(tokens);

        WACCParser.ProgContext tree = parser.prog();

        MyVisitor visitor = new MyVisitor();
        visitor.visit(tree);
    }

}

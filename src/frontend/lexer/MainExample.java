package frontend.lexer;

import antlr.BasicLexer;
import antlr.BasicParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class MainExample {

    public static void main(String[] args) throws IOException {
        CharStream input = new ANTLRInputStream(System.in);

        BasicLexer lexer = new BasicLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        BasicParser parser = new BasicParser(tokens);

        BasicParser.ProgContext tree = parser.prog();

        MyVisitor visitor = new MyVisitor();
        visitor.visit(tree);
    }

}

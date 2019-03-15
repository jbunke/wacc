import antlr.WACCLexer;
import antlr.WACCParser;
import backend.AssemblyGenerator;
import frontend.Visitor;
import frontend.WACCParserErrorListener;
import frontend.abstractSyntaxTree.ProgramNode;
import frontend.symbolTable.SemanticErrorList;
import frontend.symbolTable.SymbolTable;
import org.antlr.v4.runtime.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class WACCCompiler {

  private static final int EXPECTED_NUM_ARGS = 1;
  private static final int FILENAME_ARG_INDEX = 0;

  private static final int INCORRECT_ARGS_EXIT = 1;
  private static final int FILE_ERROR_EXIT = 1;
  private static final int SYNTAX_ERROR_EXIT = 100;
  private static final int SEMANTIC_ERROR_EXIT = 200;

  public static void main(String[] args) {
    // Test arguments are correct

    if (args.length != EXPECTED_NUM_ARGS) {
      System.out.println(args.length + " arguments given, " +
              EXPECTED_NUM_ARGS + " expected.");
      System.exit(INCORRECT_ARGS_EXIT);
    }

    String file = args[FILENAME_ARG_INDEX];

    if (!file.endsWith(".wacc")) {
      System.out.println("File provided is not of type .wacc");
      System.exit(FILE_ERROR_EXIT);
    }

    try {

      CharStream input = CharStreams.fromFileName(file);
      WACCLexer lexer = new WACCLexer(input);

      lexer.removeErrorListeners();                        // Remove the ANTLR
      // default error
      TokenStream tokens = new CommonTokenStream(lexer);   // listeners so that
      WACCParser parser = new WACCParser(tokens);          // our own can be
      // used without
      parser.removeErrorListeners();                       // multiple errors
      // being raised from
      WACCParserErrorListener syntaxErrorListener =        // a single syntax
              new WACCParserErrorListener();                   // error encounter.
      parser.addErrorListener(syntaxErrorListener);

      WACCParser.ProgContext parseTree = parser.prog();

      // Check parser for syntax errors
      if (syntaxErrorListener.hasError()) {
        for (String error : syntaxErrorListener.getSyntaxErrors()) {
          System.out.println(error);
        }
        // Semantic error exit code as per WACC specification is 100
        System.exit(SYNTAX_ERROR_EXIT);
      }

      Visitor visitor = new Visitor();
      ProgramNode AST = (ProgramNode) visitor.visit(parseTree);

      SymbolTable topLevelSymbolTable = new SymbolTable(null, AST);
      SemanticErrorList semErrors = new SemanticErrorList();

      List<String> synErrors = AST.syntaxCheck();

      // Secondary syntax error check from AST
      if (!synErrors.isEmpty()) {
        for (String error : synErrors) {
          System.out.println("Syntax Error: " + error);
        }
        System.exit(SYNTAX_ERROR_EXIT);
      }

      /* Semantic Check is run from program-level and
       * cascades down AST through sub-trees */
      AST.semanticCheck(topLevelSymbolTable, semErrors);

      if (semErrors.errorsExist()) {
        // Print errors to standard output
        semErrors.printErrors(System.out);
        // Semantic error exit code as per WACC specification is 200
        System.exit(SEMANTIC_ERROR_EXIT);
      }

      String assFileName = file.substring(file.lastIndexOf("/") + 1,
              file.lastIndexOf(".wacc")) + ".s";
      File assFile = new File(assFileName);

      if (!assFile.createNewFile()) {
        System.out.println("File: " + assFileName + " already exists");
        System.exit(FILE_ERROR_EXIT);
      }

      AssemblyGenerator programGenerator =
              new AssemblyGenerator(AST, topLevelSymbolTable);
      programGenerator.generateAssembly(assFile);
    } catch (IOException e) {
      System.out.println("File at path (" + file + ") doesn't exist");
      System.exit(FILE_ERROR_EXIT);
    } catch (RecognitionException e) {
      System.out.println("Error with prediction, predicate failure or "
              + "mismatched input occurred.");
    }
  }
}
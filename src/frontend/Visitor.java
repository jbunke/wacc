package frontend;

import antlr.BasicParser;
import antlr.BasicParserVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Visitor implements BasicParserVisitor<Void> {

  public Void visitBinaryOper(BasicParser.BinaryOperContext ctx) {
    return null;
  }

  public Void visitExpr(BasicParser.ExprContext ctx) {
    return null;
  }

  public Void visitProg(BasicParser.ProgContext ctx) {
    return null;
  }

  public Void visit(ParseTree tree) {
    return null;
  }

  public Void visitChildren(RuleNode node) {
    return null;
  }

  public Void visitTerminal(TerminalNode node) {
    return null;
  }

  public Void visitErrorNode(ErrorNode node) {
    return null;
  }
}

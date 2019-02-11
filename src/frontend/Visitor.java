package frontend;

import antlr.WACCParser;
import antlr.WACCParserVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Visitor implements WACCParserVisitor<Void> {

  public Void visitBinaryOper(WACCParser.BinaryOperContext ctx) {
    return null;
  }

  public Void visitExpr(WACCParser.ExprContext ctx) {
    return null;
  }

  public Void visitProg(WACCParser.ProgContext ctx) {
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

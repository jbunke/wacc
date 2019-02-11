package frontend;

import antlr.WACCParser;
import antlr.WACCParserVisitor;
import frontend.AbstractSyntaxTree.Expressions.*;
import frontend.AbstractSyntaxTree.Node;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Visitor implements WACCParserVisitor<Node> {

  @Override
  public Node visitUnaryOper(WACCParser.UnaryOperContext ctx) {
    return null;
  }

  @Override
  public Node visitBinaryOper(WACCParser.BinaryOperContext ctx) {
    return null;
  }

  @Override
  public Node visitIdentifier(WACCParser.IdentifierContext ctx) {
    return null;
  }

  public Node visitExpr(WACCParser.ExprContext ctx) {
    return null;
  }

  @Override
  public Node visitStat(WACCParser.StatContext ctx) {
    return null;
  }

  @Override
  public Node visitIntLiteral(WACCParser.IntLiteralContext ctx) {
    Integer intLit = Integer.parseInt(ctx.getText());
    return new IntLiteralExpressionNode(intLit);
  }

  @Override
  public Node visitBoolLiteral(WACCParser.BoolLiteralContext ctx) {
    Boolean boolLit = Boolean.parseBoolean(ctx.getText());
    return new BooleanLiteralExpressionNode(boolLit);
  }

  @Override
  public Node visitCharLiteral(WACCParser.CharLiteralContext ctx) {
    Character charLit = ctx.CHAR_LIT().getText().charAt(0);
    return new CharacterLiteralExpressionNode(charLit);
  }

  @Override
  public Node visitStringLiteral(WACCParser.StringLiteralContext ctx) {
    String stringLit = ctx.getText();
    return new StringLiteralExpressionNode(stringLit);
  }

  @Override
  public Node visitPairLiter(WACCParser.PairLiterContext ctx) {
    return new PairLiteralExpressionNode();
  }

  @Override
  public Node visitType(WACCParser.TypeContext ctx) {
    return null;
  }

  @Override
  public Node visitBaseType(WACCParser.BaseTypeContext ctx) {
    return null;
  }

  @Override
  public Node visitArrayElem(WACCParser.ArrayElemContext ctx) {
    return null;
  }

  @Override
  public Node visitArrayType(WACCParser.ArrayTypeContext ctx) {
    return null;
  }

  @Override
  public Node visitArrayLiteral(WACCParser.ArrayLiteralContext ctx) {
    return null;
  }

  @Override
  public Node visitPairElem(WACCParser.PairElemContext ctx) {
    return null;
  }

  @Override
  public Node visitPairElemType(WACCParser.PairElemTypeContext ctx) {
    return null;
  }

  @Override
  public Node visitPairType(WACCParser.PairTypeContext ctx) {
    return null;
  }

  @Override
  public Node visitFunc(WACCParser.FuncContext ctx) {
    return null;
  }

  @Override
  public Node visitArgList(WACCParser.ArgListContext ctx) {
    return null;
  }

  @Override
  public Node visitParam(WACCParser.ParamContext ctx) {
    return null;
  }

  @Override
  public Node visitParamList(WACCParser.ParamListContext ctx) {
    return null;
  }

  @Override
  public Node visitAssignLhs(WACCParser.AssignLhsContext ctx) {
    return null;
  }

  @Override
  public Node visitAssignRhs(WACCParser.AssignRhsContext ctx) {
    return null;
  }

  public Node visitProg(WACCParser.ProgContext ctx) {
    return null;
  }

  public Node visit(ParseTree tree) {
    return null;
  }

  public Node visitChildren(RuleNode node) {
    return null;
  }

  public Node visitTerminal(TerminalNode node) {
    return null;
  }

  public Node visitErrorNode(ErrorNode node) {
    return null;
  }
}

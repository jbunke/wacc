package frontend;

import antlr.WACCParser;
import antlr.WACCParserVisitor;
import frontend.AbstractSyntaxTree.Expressions.*;
import frontend.AbstractSyntaxTree.Node;
import frontend.AbstractSyntaxTree.TypeNodes.PairTypeNode;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Visitor implements WACCParserVisitor<Node> {

  @Override
  public Node visitIdentifierExp(WACCParser.IdentifierExpContext ctx) {
    return visitIdentifier(ctx.identifier());
  }

  @Override
  public Node visitAndExp(WACCParser.AndExpContext ctx) {
    return null;
  }

  @Override
  public Node visitStringLitExp(WACCParser.StringLitExpContext ctx) {
    return visitStringLiteral(ctx.stringLiteral());
  }

  @Override
  public Node visitMultDivModExp(WACCParser.MultDivModExpContext ctx) {
    return null;
  }

  @Override
  public Node visitArrayElemExp(WACCParser.ArrayElemExpContext ctx) {
    return visitArrayElem(ctx.arrayElem());
  }

  @Override
  public Node visitUnaryOperExp(WACCParser.UnaryOperExpContext ctx) {
    //return new UnaryOpExpressionNode(ctx.op.toString(), (ExpressionNode)visit(ctx));
    return null;
  }

  @Override
  public Node visitBracketedExpr(WACCParser.BracketedExprContext ctx) {
    return visit(ctx.expr());
  }

  @Override
  public Node visitIntLitExp(WACCParser.IntLitExpContext ctx) {
    return visitIntLiteral(ctx.intLiteral());
  }

  @Override
  public Node visitPairLitExp(WACCParser.PairLitExpContext ctx) {
    return visitPairLiter(ctx.pairLiter());
  }

  @Override
  public Node visitCompEqExp(WACCParser.CompEqExpContext ctx) {
    return null;
  }

  @Override
  public Node visitCompLsGrExp(WACCParser.CompLsGrExpContext ctx) {
    return null;
  }

  @Override
  public Node visitOrExp(WACCParser.OrExpContext ctx) {
    return null;
  }

  @Override
  public Node visitCharLitExp(WACCParser.CharLitExpContext ctx) {
    return visitCharLiteral(ctx.charLiteral());
  }

  @Override
  public Node visitAddSubExp(WACCParser.AddSubExpContext ctx) {
    return null;
  }

  @Override
  public Node visitBoolLitExp(WACCParser.BoolLitExpContext ctx) {
    return visitBoolLiteral(ctx.boolLiteral());
  }

  @Override
  public Node visitReturnStat(WACCParser.ReturnStatContext ctx) {
    return null;
  }

  @Override
  public Node visitExitStat(WACCParser.ExitStatContext ctx) {
    return null;
  }

  @Override
  public Node visitScopeStat(WACCParser.ScopeStatContext ctx) {
    return null;
  }

  @Override
  public Node visitFreeStat(WACCParser.FreeStatContext ctx) {
    return null;
  }

  @Override
  public Node visitSkipStat(WACCParser.SkipStatContext ctx) {
    return null;
  }

  @Override
  public Node visitInitAssignStat(WACCParser.InitAssignStatContext ctx) {
    return null;
  }

  @Override
  public Node visitWhileStat(WACCParser.WhileStatContext ctx) {
    return null;
  }

  @Override
  public Node visitReadStat(WACCParser.ReadStatContext ctx) {
    return null;
  }

  @Override
  public Node visitStatSeq(WACCParser.StatSeqContext ctx) {
    return null;
  }

  @Override
  public Node visitPrintlnStat(WACCParser.PrintlnStatContext ctx) {
    return null;
  }

  @Override
  public Node visitAssignStat(WACCParser.AssignStatContext ctx) {
    return null;
  }

  @Override
  public Node visitCondStat(WACCParser.CondStatContext ctx) {
    return null;
  }

  @Override
  public Node visitPrintStat(WACCParser.PrintStatContext ctx) {
    return null;
  }

  @Override
  public Node visitUnaryOper(WACCParser.UnaryOperContext ctx) {
    return null;
  }

  @Override
  public Node visitMultDivMod(WACCParser.MultDivModContext ctx) {
    return null;
  }

  @Override
  public Node visitAddSub(WACCParser.AddSubContext ctx) {
    return null;
  }

  @Override
  public Node visitCompLsGr(WACCParser.CompLsGrContext ctx) {
    return null;
  }

  @Override
  public Node visitCompEq(WACCParser.CompEqContext ctx) {
    return null;
  }

  @Override
  public Node visitIdentifier(WACCParser.IdentifierContext ctx) {
    return null;
  }

  @Override
  public Node visitIntLiteral(WACCParser.IntLiteralContext ctx) {
    return new IntLiteralExpressionNode(Integer.parseInt(ctx.getText()));
  }

  @Override
  public Node visitBoolLiteral(WACCParser.BoolLiteralContext ctx) {
    return new BooleanLiteralExpressionNode(Boolean.parseBoolean(ctx.getText()));
  }

  @Override
  public Node visitCharLiteral(WACCParser.CharLiteralContext ctx) {
    return new CharacterLiteralExpressionNode(ctx.CHAR_LIT().getText().charAt(0));
  }

  @Override
  public Node visitStringLiteral(WACCParser.StringLiteralContext ctx) {
    return new StringLiteralExpressionNode(ctx.getText());
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

  @Override
  public Node visitProg(WACCParser.ProgContext ctx) {
    return null;
  }

  @Override
  public Node visit(ParseTree tree) {
    return null;
  }

  @Override
  public Node visitChildren(RuleNode node) {
    return null;
  }

  @Override
  public Node visitTerminal(TerminalNode node) {
    return null;
  }

  @Override
  public Node visitErrorNode(ErrorNode node) {
    return null;
  }
}
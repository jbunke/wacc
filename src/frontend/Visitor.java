package frontend;

import antlr.WACCParser;
import antlr.WACCParserVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Visitor implements WACCParserVisitor<Void> {

  @Override
  public Void visitUnaryOper(WACCParser.UnaryOperContext ctx) {
    return null;
  }

  public Void visitBinaryOper(WACCParser.BinaryOperContext ctx) {
    return null;
  }

  @Override
  public Void visitIdentifier(WACCParser.IdentifierContext ctx) {
    return null;
  }

  public Void visitExpr(WACCParser.ExprContext ctx) {
    return null;
  }

  @Override
  public Void visitStat(WACCParser.StatContext ctx) {
    return null;
  }

  @Override
  public Void visitIntSign(WACCParser.IntSignContext ctx) {
    return null;
  }

  @Override
  public Void visitIntLiteral(WACCParser.IntLiteralContext ctx) {
    return null;
  }

  @Override
  public Void visitBoolLiteral(WACCParser.BoolLiteralContext ctx) {
    return null;
  }

  @Override
  public Void visitCharLiteral(WACCParser.CharLiteralContext ctx) {
    return null;
  }

  @Override
  public Void visitStringLiteral(WACCParser.StringLiteralContext ctx) {
    return null;
  }

  @Override
  public Void visitPairLiter(WACCParser.PairLiterContext ctx) {
    return null;
  }

  @Override
  public Void visitType(WACCParser.TypeContext ctx) {
    return null;
  }

  @Override
  public Void visitBaseType(WACCParser.BaseTypeContext ctx) {
    return null;
  }

  @Override
  public Void visitArrayElem(WACCParser.ArrayElemContext ctx) {
    return null;
  }

  @Override
  public Void visitArrayType(WACCParser.ArrayTypeContext ctx) {
    return null;
  }

  @Override
  public Void visitArrayLiteral(WACCParser.ArrayLiteralContext ctx) {
    return null;
  }

  @Override
  public Void visitPairElem(WACCParser.PairElemContext ctx) {
    return null;
  }

  @Override
  public Void visitPairElemType(WACCParser.PairElemTypeContext ctx) {
    return null;
  }

  @Override
  public Void visitPairType(WACCParser.PairTypeContext ctx) {
    return null;
  }

  @Override
  public Void visitFunc(WACCParser.FuncContext ctx) {
    return null;
  }

  @Override
  public Void visitArgList(WACCParser.ArgListContext ctx) {
    return null;
  }

  @Override
  public Void visitParam(WACCParser.ParamContext ctx) {
    return null;
  }

  @Override
  public Void visitParamList(WACCParser.ParamListContext ctx) {
    return null;
  }

  @Override
  public Void visitAssignLhs(WACCParser.AssignLhsContext ctx) {
    return null;
  }

  @Override
  public Void visitAssignRhs(WACCParser.AssignRhsContext ctx) {
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

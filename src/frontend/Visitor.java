package frontend;

import antlr.WACCParser;
import antlr.WACCParserVisitor;
import frontend.AbstractSyntaxTree.Assignment.AssignLHS;
import frontend.AbstractSyntaxTree.Assignment.AssignRHS;
import frontend.AbstractSyntaxTree.Expressions.*;
import frontend.AbstractSyntaxTree.Node;
import frontend.AbstractSyntaxTree.Statements.DeclarationStatementNode;
import frontend.AbstractSyntaxTree.TypeNodes.ArrayTypeNode;
import frontend.AbstractSyntaxTree.TypeNodes.BaseTypesNode;
import frontend.AbstractSyntaxTree.TypeNodes.TypeNode;
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
    String operator = ctx.AND().getText();
    ExpressionNode left = (ExpressionNode) visit(ctx.expr(0));
    ExpressionNode right = (ExpressionNode) visit(ctx.expr(1));
    return new BinaryOpExpressionNode(left, operator, right);
  }

  @Override
  public Node visitStringLitExp(WACCParser.StringLitExpContext ctx) {
    return visitStringLiteral(ctx.stringLiteral());
  }

  @Override
  public Node visitMultDivModExp(WACCParser.MultDivModExpContext ctx) {
    String operator = ctx.MULTDIVMOD().getText();
    ExpressionNode left = (ExpressionNode) visit(ctx.expr(0));
    ExpressionNode right = (ExpressionNode) visit(ctx.expr(1));
    return new BinaryOpExpressionNode(left, operator, right);
  }

  @Override
  public Node visitArrayElemExp(WACCParser.ArrayElemExpContext ctx) {
    return visitArrayElem(ctx.arrayElem());
  }

  @Override
  public Node visitUnaryOperExp(WACCParser.UnaryOperExpContext ctx) {
    String operator = ctx.UNARY().getText();
    ExpressionNode expression = (ExpressionNode) visit(ctx.expr());
    return new UnaryOpExpressionNode(operator, expression);
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
    String operator = ctx.COMP_EQ().getText();
    ExpressionNode left = (ExpressionNode) visit(ctx.expr(0));
    ExpressionNode right = (ExpressionNode) visit(ctx.expr(1));
    return new BinaryOpExpressionNode(left, operator, right);
  }

  @Override
  public Node visitCompLsGrExp(WACCParser.CompLsGrExpContext ctx) {
    String operator = ctx.COMP_LS_GR().getText();
    ExpressionNode left = (ExpressionNode) visit(ctx.expr(0));
    ExpressionNode right = (ExpressionNode) visit(ctx.expr(1));
    return new BinaryOpExpressionNode(left, operator, right);
  }

  @Override
  public Node visitOrExp(WACCParser.OrExpContext ctx) {
    String operator = ctx.OR().getText();
    ExpressionNode left = (ExpressionNode) visit(ctx.expr(0));
    ExpressionNode right = (ExpressionNode) visit(ctx.expr(1));
    return new BinaryOpExpressionNode(left, operator, right);
  }

  @Override
  public Node visitCharLitExp(WACCParser.CharLitExpContext ctx) {
    return visitCharLiteral(ctx.charLiteral());
  }

  @Override
  public Node visitAddSubExp(WACCParser.AddSubExpContext ctx) {
    String operator = ctx.ADDSUB().getText();
    ExpressionNode left = (ExpressionNode) visit(ctx.expr(0));
    ExpressionNode right = (ExpressionNode) visit(ctx.expr(1));
    return new BinaryOpExpressionNode(left, operator, right);
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
    TypeNode typeNode = (TypeNode) visit(ctx.type());
    IdentifierNode ident = new IdentifierNode(ctx.IDENTIFIER().getText());
    AssignRHS rhs = (AssignRHS) visit(ctx.assignRhs());

    return new DeclarationStatementNode(typeNode, ident, rhs);
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
    AssignLHS lhs = (AssignLHS) visit(ctx.assignLhs());
    AssignRHS rhs = (AssignRHS) visit(ctx.assignRhs());
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
  public Node visitIdentifier(WACCParser.IdentifierContext ctx) {
    return new IdentifierNode(ctx.getText());
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
  public Node visitBaseTp(WACCParser.BaseTpContext ctx) {
    return visitBaseType(ctx.baseType());
  }

  @Override
  public Node visitArrayTp(WACCParser.ArrayTpContext ctx) {
    TypeNode arrayType = (TypeNode) visit(ctx.type());
    return new ArrayTypeNode(arrayType);
  }

  @Override
  public Node visitPairTp(WACCParser.PairTpContext ctx) {
    return null;
  }

  @Override
  public Node visitBaseType(WACCParser.BaseTypeContext ctx) {
    return new BaseTypesNode(ctx.getText());
  }

  @Override
  public Node visitArrayElem(WACCParser.ArrayElemContext ctx) {
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
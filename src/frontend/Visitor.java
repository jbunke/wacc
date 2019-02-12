package frontend;

import antlr.WACCParser;
import antlr.WACCParserVisitor;
import frontend.abstractSyntaxTree.assignment.ArrayLiteralNode;
import frontend.abstractSyntaxTree.assignment.AssignLHS;
import frontend.abstractSyntaxTree.assignment.AssignPairElementNode;
import frontend.abstractSyntaxTree.assignment.AssignRHS;
import frontend.abstractSyntaxTree.expressions.*;
import frontend.abstractSyntaxTree.Node;
import frontend.abstractSyntaxTree.statements.*;
import frontend.abstractSyntaxTree.typeNodes.ArrayTypeNode;
import frontend.abstractSyntaxTree.typeNodes.BaseTypesNode;
import frontend.abstractSyntaxTree.typeNodes.PairTypeNode;
import frontend.abstractSyntaxTree.typeNodes.TypeNode;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

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
    ExpressionNode expression = (ExpressionNode) visit(ctx.expr());
    return new ReturnStatementNode(expression);
  }

  @Override
  public Node visitExitStat(WACCParser.ExitStatContext ctx) {
    ExpressionNode expression = (ExpressionNode) visit(ctx.expr());
    return new ExitStatementNode(expression);
  }

  @Override
  public Node visitScopeStat(WACCParser.ScopeStatContext ctx) {
    StatementNode stat = (StatementNode) visit(ctx.stat());
    return new InnerScopeStatementNode(stat);
  }

  @Override
  public Node visitFreeStat(WACCParser.FreeStatContext ctx) {
    ExpressionNode expression = (ExpressionNode) visit(ctx.expr());
    return new FreeStatementNode(expression);
  }

  @Override
  public Node visitSkipStat(WACCParser.SkipStatContext ctx) {
    return new SkipStatementNode();
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
    ExpressionNode cond = (ExpressionNode) visit(ctx.expr());
    StatementNode stat = (StatementNode) visit(ctx.stat());
    return new WhileStatementNode(cond, stat);
  }

  @Override
  public Node visitReadStat(WACCParser.ReadStatContext ctx) {
    AssignLHS lhs = (AssignLHS) visit(ctx.assignLhs());
    return new ReadStatementNode(lhs);
  }

  @Override
  public Node visitStatSeq(WACCParser.StatSeqContext ctx) {
    List<WACCParser.StatContext> statements = ctx.stat();
    List<StatementNode> nodes = new ArrayList<>();

    for (WACCParser.StatContext statContext : statements) {
      nodes.add((StatementNode) visit(statContext));
    }
    return new StatementListNode(nodes);
  }

  @Override
  public Node visitPrintlnStat(WACCParser.PrintlnStatContext ctx) {
    ExpressionNode expression = (ExpressionNode) visit(ctx.expr());
    return new PrintLineStatementNode(expression);
  }

  @Override
  public Node visitAssignStat(WACCParser.AssignStatContext ctx) {
    AssignLHS lhs = (AssignLHS) visit(ctx.assignLhs());
    AssignRHS rhs = (AssignRHS) visit(ctx.assignRhs());
    return new AssignVariableStatementNode(lhs, rhs);
  }

  @Override
  public Node visitCondStat(WACCParser.CondStatContext ctx) {
    ExpressionNode cond = (ExpressionNode) visit(ctx.expr());
    StatementNode tr = (StatementNode) visit(ctx.stat(0));
    StatementNode fl = (StatementNode) visit(ctx.stat(1));

    return new IfStatementNode(cond, tr, fl);
  }

  @Override
  public Node visitPrintStat(WACCParser.PrintStatContext ctx) {
    ExpressionNode expression = (ExpressionNode) visit(ctx.expr());
    return new PrintStatementNode(expression);
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
    TypeNode fst = (TypeNode) visit(ctx.pairElemType(0));
    TypeNode snd = (TypeNode) visit(ctx.pairElemType(1));
    return new PairTypeNode(fst, snd);
  }

  @Override
  public Node visitBaseType(WACCParser.BaseTypeContext ctx) {
    return new BaseTypesNode(ctx.getText());
  }

  @Override
  public Node visitArrayElem(WACCParser.ArrayElemContext ctx) {
    IdentifierNode ident = new IdentifierNode(ctx.IDENTIFIER().getText());
    List<ExpressionNode> indices = new ArrayList<>();
    for (WACCParser.ExprContext expr : ctx.expr()) {
      indices.add((ExpressionNode) visit(expr));
    }
    return new ArrayElementNode(indices, ident);
  }

  @Override
  public Node visitArrayLiteral(WACCParser.ArrayLiteralContext ctx) {
    List<ExpressionNode> elems = new ArrayList<>();
    for (WACCParser.ExprContext exprContext : ctx.expr()) {
      elems.add((ExpressionNode) visit(exprContext));
    }
    return new ArrayLiteralNode(elems);
  }

  @Override
  public Node visitFstElem(WACCParser.FstElemContext ctx) {
    IdentifierNode ident = (IdentifierNode) visit(ctx.expr());
    return new AssignPairElementNode(ident, 0);
  }

  @Override
  public Node visitSndElem(WACCParser.SndElemContext ctx) {
    IdentifierNode ident = (IdentifierNode) visit(ctx.expr());
    return new AssignPairElementNode(ident, 1);
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
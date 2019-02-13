package frontend;

import antlr.WACCParser;
import antlr.WACCParserBaseVisitor;
import frontend.abstractSyntaxTree.ProgramNode;
import frontend.abstractSyntaxTree.assignment.*;
import frontend.abstractSyntaxTree.expressions.*;
import frontend.abstractSyntaxTree.Node;
import frontend.abstractSyntaxTree.statements.*;
import frontend.abstractSyntaxTree.typeNodes.*;

import java.util.ArrayList;
import java.util.List;

public class Visitor extends WACCParserBaseVisitor<Node> {

  @Override
  public Node visitIdentifierExp(WACCParser.IdentifierExpContext ctx) {
    return new IdentifierNode(ctx.IDENTIFIER().getText());
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
    int value = Integer.parseInt(ctx.intLiteral().INT_LIT().getText());
    return new IntLiteralExpressionNode(value);
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
    // TODO: Comment code
    List<StatementNode> statements = new ArrayList<>();
    StatementNode node1 = (StatementNode) visit(ctx.stat(0));
    StatementNode node2 = (StatementNode) visit(ctx.stat(1));

    if (node1 instanceof StatementListNode) {
      StatementListNode unfolded = (StatementListNode) node1;
      statements.addAll(unfolded.getStatements());
    } else {
      statements.add(node1);
    }
    statements.add(node2);
    return new StatementListNode(statements);
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
  public Node visitPairElemTypeBase(WACCParser.PairElemTypeBaseContext ctx) {
    return visitBaseType(ctx.baseType());
  }

  @Override
  public Node visitPairElemTypeArray(WACCParser.PairElemTypeArrayContext ctx) {
    return visit(ctx.type());
  }

  @Override
  public Node visitPairElemTypePair(WACCParser.PairElemTypePairContext ctx) {
    return new InnerPairTypeNode();
  }

  @Override
  public Node visitFunc(WACCParser.FuncContext ctx) {
    TypeNode typeNode = (TypeNode) visit(ctx.type());
    IdentifierNode ident = new IdentifierNode(ctx.IDENTIFIER().getText());
    StatementNode stat = (StatementNode) visit(ctx.stat());

    if (ctx.paramList() != null) {
      ParameterListNode params = (ParameterListNode) visitParamList(ctx.paramList());
      return new FunctionDefinitionNode(typeNode, ident, params, stat);
    }

    return new FunctionDefinitionNode(typeNode, ident,
            new ParameterListNode(new ArrayList<>(), new ArrayList<>()), stat);
  }

  @Override
  public Node visitParamList(WACCParser.ParamListContext ctx) {
    List<IdentifierNode> paramNames = new ArrayList<>();
    List<TypeNode> paramTypes = new ArrayList<>();

    if (ctx.IDENTIFIER() != null) {
      for (int i = 0; i < ctx.IDENTIFIER().size(); i++) {
        paramNames.add(
                new IdentifierNode(ctx.IDENTIFIER(i).getText()));
        paramTypes.add((TypeNode) visit(ctx.type(i)));
      }
    }

    return new ParameterListNode(paramNames, paramTypes);
  }

  @Override
  public Node visitLHSIdent(WACCParser.LHSIdentContext ctx) {
    return new IdentifierNode(ctx.IDENTIFIER().getText());
  }

  @Override
  public Node visitLHSArrayElem(WACCParser.LHSArrayElemContext ctx) {
    return visitArrayElem(ctx.arrayElem());
  }

  @Override
  public Node visitLHSPairElem(WACCParser.LHSPairElemContext ctx) {
    return visit(ctx.pairElem());
  }

  @Override
  public Node visitRHSExpr(WACCParser.RHSExprContext ctx) {
    return visit(ctx.expr());
  }

  @Override
  public Node visitRHSArrayLit(WACCParser.RHSArrayLitContext ctx) {
    return visitArrayLiteral(ctx.arrayLiteral());
  }

  @Override
  public Node visitRHSNewPair(WACCParser.RHSNewPairContext ctx) {
    ExpressionNode left = (ExpressionNode) visit(ctx.expr(0));
    ExpressionNode right = (ExpressionNode) visit(ctx.expr(1));
    return new NewPairNode(left, right);
  }

  @Override
  public Node visitRHSPairElem(WACCParser.RHSPairElemContext ctx) {
    return visit(ctx.pairElem());
  }

  @Override
  public Node visitRHSFunctionCall(WACCParser.RHSFunctionCallContext ctx) {
    IdentifierNode functName = new IdentifierNode(ctx.IDENTIFIER().getText());
    List<ExpressionNode> args = new ArrayList<>();

    for (WACCParser.ExprContext arg : ctx.expr()) {
      args.add((ExpressionNode) visit(arg));
    }

    return new FunctionCallNode(functName, args);
  }

  @Override
  public Node visitProg(WACCParser.ProgContext ctx) {
    StatementNode stat = (StatementNode) visit(ctx.stat());

    List<FunctionDefinitionNode> functions = new ArrayList<>();
    for (WACCParser.FuncContext fctx : ctx.func()) {
      functions.add((FunctionDefinitionNode) visitFunc(fctx));
    }

    return new ProgramNode(functions, stat);
  }
}
package frontend.lexer;

import antlr.WACCParser;
import antlr.WACCParserVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class MyVisitor implements WACCParserVisitor<Void>{

    @Override
    public Void visitUnaryOper(WACCParser.UnaryOperContext ctx) {
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

    @Override
    public Void visitStat(WACCParser.StatContext ctx) {
        return null;
    }

    @Override
    public Void visitBinaryOper(WACCParser.BinaryOperContext ctx) {
        return null;
    }

    @Override
    public Void visitExpr(WACCParser.ExprContext ctx) {
        return null;
    }

    @Override
    public Void visitProg(WACCParser.ProgContext ctx) {
        System.out.println("Visiting program");
        return null;
    }

    @Override
    public Void visit(ParseTree parseTree) {
        return null;
    }

    @Override
    public Void visitChildren(RuleNode ruleNode) {
        return null;
    }

    @Override
    public Void visitTerminal(TerminalNode terminalNode) {
        return null;
    }

    @Override
    public Void visitErrorNode(ErrorNode errorNode) {
        return null;
    }
}

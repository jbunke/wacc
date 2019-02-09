package frontend.lexer;

import antlr.WACCParser;
import antlr.WACCParserVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class MyVisitor implements WACCParserVisitor<Void>{
    public Void visitUnaryOper(WACCParser.UnaryOperContext ctx) {
        return null;
    }

    public Void visitBinaryOper(WACCParser.BinaryOperContext ctx) {
        return null;
    }

    public Void visitIdentifier(WACCParser.IdentifierContext ctx) {
        return null;
    }

    public Void visitExpr(WACCParser.ExprContext ctx) {
        return null;
    }

    public Void visitIntSign(WACCParser.IntSignContext ctx) {
        return null;
    }

    public Void visitIntLiteral(WACCParser.IntLiteralContext ctx) {
        return null;
    }

    public Void visitBoolLiteral(WACCParser.BoolLiteralContext ctx) {
        return null;
    }

    public Void visitCharacter(WACCParser.CharacterContext ctx) {
        return null;
    }

    public Void visitCharLiteral(WACCParser.CharLiteralContext ctx) {
        return null;
    }

    public Void visitStringLiteral(WACCParser.StringLiteralContext ctx) {
        return null;
    }

    public Void visitPairLiter(WACCParser.PairLiterContext ctx) {
        return null;
    }

    public Void visitType(WACCParser.TypeContext ctx) {
        return null;
    }

    public Void visitBaseType(WACCParser.BaseTypeContext ctx) {
        return null;
    }

    public Void visitArrayElem(WACCParser.ArrayElemContext ctx) {
        return null;
    }

    public Void visitArrayType(WACCParser.ArrayTypeContext ctx) {
        return null;
    }

    public Void visitArrayLiteral(WACCParser.ArrayLiteralContext ctx) {
        return null;
    }

    public Void visitPairElem(WACCParser.PairElemContext ctx) {
        return null;
    }

    public Void visitPairElemType(WACCParser.PairElemTypeContext ctx) {
        return null;
    }

    public Void visitPairType(WACCParser.PairTypeContext ctx) {
        return null;
    }

    public Void visitFunc(WACCParser.FuncContext ctx) {
        return null;
    }

    public Void visitArgList(WACCParser.ArgListContext ctx) {
        return null;
    }

    public Void visitParam(WACCParser.ParamContext ctx) {
        return null;
    }

    public Void visitParamList(WACCParser.ParamListContext ctx) {
        return null;
    }

    public Void visitAssignLhs(WACCParser.AssignLhsContext ctx) {
        return null;
    }

    public Void visitAssignRhs(WACCParser.AssignRhsContext ctx) {
        return null;
    }

    public Void visitStat(WACCParser.StatContext ctx) {
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

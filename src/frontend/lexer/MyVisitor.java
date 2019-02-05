package frontend.lexer;

import antlr.BasicParser;
import antlr.BasicParserVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class MyVisitor implements BasicParserVisitor<Void>{

    @Override
    public Void visitBinaryOper(BasicParser.BinaryOperContext ctx) {
        return null;
    }

    @Override
    public Void visitExpr(BasicParser.ExprContext ctx) {
        return null;
    }

    @Override
    public Void visitProg(BasicParser.ProgContext ctx) {
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

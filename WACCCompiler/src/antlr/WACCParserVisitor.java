// Generated from ./WACCParser.g4 by ANTLR 4.7
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link WACCParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface WACCParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code IdentifierExp}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierExp(WACCParser.IdentifierExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AndExp}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExp(WACCParser.AndExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringLitExp}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLitExp(WACCParser.StringLitExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultDivModExp}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultDivModExp(WACCParser.MultDivModExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayElemExp}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayElemExp(WACCParser.ArrayElemExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryOperExp}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperExp(WACCParser.UnaryOperExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BracketedExpr}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracketedExpr(WACCParser.BracketedExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntLitExp}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntLitExp(WACCParser.IntLitExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PairLitExp}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairLitExp(WACCParser.PairLitExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CompEqExp}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompEqExp(WACCParser.CompEqExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CompLsGrExp}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompLsGrExp(WACCParser.CompLsGrExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OrExp}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExp(WACCParser.OrExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CharLitExp}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharLitExp(WACCParser.CharLitExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSubExp}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExp(WACCParser.AddSubExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoolLitExp}
	 * labeled alternative in {@link WACCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLitExp(WACCParser.BoolLitExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(WACCParser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReturnStat}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStat(WACCParser.ReturnStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExitStat}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExitStat(WACCParser.ExitStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ScopeStat}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScopeStat(WACCParser.ScopeStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FreeStat}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFreeStat(WACCParser.FreeStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SkipStat}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkipStat(WACCParser.SkipStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InitAssignStat}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitAssignStat(WACCParser.InitAssignStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WhileStat}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStat(WACCParser.WhileStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReadStat}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadStat(WACCParser.ReadStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatSeq}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatSeq(WACCParser.StatSeqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrintlnStat}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintlnStat(WACCParser.PrintlnStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignStat}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStat(WACCParser.AssignStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CondStat}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondStat(WACCParser.CondStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrintStat}
	 * labeled alternative in {@link WACCParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStat(WACCParser.PrintStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#intLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntLiteral(WACCParser.IntLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#boolLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLiteral(WACCParser.BoolLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(WACCParser.IdentContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#charLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharLiteral(WACCParser.CharLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#stringLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(WACCParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#pairLiter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairLiter(WACCParser.PairLiterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BaseTp}
	 * labeled alternative in {@link WACCParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseTp(WACCParser.BaseTpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayTp}
	 * labeled alternative in {@link WACCParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayTp(WACCParser.ArrayTpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PairTp}
	 * labeled alternative in {@link WACCParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairTp(WACCParser.PairTpContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#baseType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseType(WACCParser.BaseTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#arrayElem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayElem(WACCParser.ArrayElemContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#arrayLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiteral(WACCParser.ArrayLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FstElem}
	 * labeled alternative in {@link WACCParser#pairElem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFstElem(WACCParser.FstElemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SndElem}
	 * labeled alternative in {@link WACCParser#pairElem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSndElem(WACCParser.SndElemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PairElemTypeBase}
	 * labeled alternative in {@link WACCParser#pairElemType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairElemTypeBase(WACCParser.PairElemTypeBaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PairElemTypeArray}
	 * labeled alternative in {@link WACCParser#pairElemType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairElemTypeArray(WACCParser.PairElemTypeArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PairElemTypePair}
	 * labeled alternative in {@link WACCParser#pairElemType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairElemTypePair(WACCParser.PairElemTypePairContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc(WACCParser.FuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#paramList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamList(WACCParser.ParamListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LHSIdent}
	 * labeled alternative in {@link WACCParser#assignLhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLHSIdent(WACCParser.LHSIdentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LHSArrayElem}
	 * labeled alternative in {@link WACCParser#assignLhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLHSArrayElem(WACCParser.LHSArrayElemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LHSPairElem}
	 * labeled alternative in {@link WACCParser#assignLhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLHSPairElem(WACCParser.LHSPairElemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RHSExpr}
	 * labeled alternative in {@link WACCParser#assignRhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRHSExpr(WACCParser.RHSExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RHSArrayLit}
	 * labeled alternative in {@link WACCParser#assignRhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRHSArrayLit(WACCParser.RHSArrayLitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RHSNewPair}
	 * labeled alternative in {@link WACCParser#assignRhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRHSNewPair(WACCParser.RHSNewPairContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RHSPairElem}
	 * labeled alternative in {@link WACCParser#assignRhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRHSPairElem(WACCParser.RHSPairElemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RHSFunctionCall}
	 * labeled alternative in {@link WACCParser#assignRhs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRHSFunctionCall(WACCParser.RHSFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link WACCParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(WACCParser.ProgContext ctx);
}
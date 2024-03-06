// Generated from /home/laptop-johnny/Secretária/Uni/2ºAno/Segundo_Semestre/C/Teórica/expr/expr.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link exprParser}.
 */
public interface exprListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link exprParser#main}.
	 * @param ctx the parse tree
	 */
	void enterMain(exprParser.MainContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#main}.
	 * @param ctx the parse tree
	 */
	void exitMain(exprParser.MainContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(exprParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(exprParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(exprParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(exprParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(exprParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(exprParser.ExpressionContext ctx);
}
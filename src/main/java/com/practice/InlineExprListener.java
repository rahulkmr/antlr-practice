// Generated from com/practice/InlineExpr.g4 by ANTLR 4.7

package com.practice;
import java.util.Map;
import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link InlineExprParser}.
 */
public interface InlineExprListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link InlineExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(InlineExprParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link InlineExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(InlineExprParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link InlineExprParser#e}.
	 * @param ctx the parse tree
	 */
	void enterE(InlineExprParser.EContext ctx);
	/**
	 * Exit a parse tree produced by {@link InlineExprParser#e}.
	 * @param ctx the parse tree
	 */
	void exitE(InlineExprParser.EContext ctx);
}
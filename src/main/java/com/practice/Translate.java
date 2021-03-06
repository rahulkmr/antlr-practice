package com.practice;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

class Translate {
	public static void main(String args[]) throws Exception {
		CharStream input = CharStreams.fromStream(System.in);
		ArrayInitLexer lexer = new ArrayInitLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ArrayInitParser parser = new ArrayInitParser(tokens);
		ParseTree tree = parser.init();

		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(new ShortToUnicode(), tree);
		System.out.println();
	}
}

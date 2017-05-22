package com.practice;

import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;


public class Calc {
    public static void main(String args[]) throws Exception {
        String inputFile = null;
        if (args.length > 0)
            inputFile = args[0];
        InputStream is = System.in;
        if (inputFile != null)
            is = new FileInputStream(inputFile);
        ANTLRInputStream input = new ANTLRInputStream(is);
        ExprLexer lexer = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokens);
        ParseTree tree = parser.prog();
        EvalVisitor visitor = new EvalVisitor();
        visitor.visit(tree);
    }
}

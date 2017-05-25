package com.practice;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;


public class InteractiveCalc {
    public static void main(String[] args) throws Exception {
        String inputFile = null;
        if (args.length > 0) inputFile = args[0];
        InputStream is = System.in;
        if (inputFile != null) {
            is = new FileInputStream(inputFile);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String expr = br.readLine();
        int line = 1;
        InlineExprParser parser = new InlineExprParser(null);
        parser.setBuildParseTree(false);
        while (expr != null) {
            CharStream input = CharStreams.fromString(expr + "\n");
            InlineExprLexer lexer = new InlineExprLexer(input);
            lexer.setLine(line);
            lexer.setCharPositionInLine(0);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            parser.setTokenStream(tokens);
            parser.stat();
            expr = br.readLine();
            line++;
        }
    }
}
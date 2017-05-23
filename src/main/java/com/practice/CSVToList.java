package com.practice;

import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;


public class CSVToList {
    public static void main(String args[]) throws Exception {
        String inputFile = null;
        if (args.length > 0)
            inputFile = args[0];
        InputStream is = System.in;
        if (inputFile != null)
            is = new FileInputStream(inputFile);
        CharStream input = CharStreams.fromStream(is);
        CSVLexer lexer = new CSVLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CSVParser parser = new CSVParser(tokens);
        ParseTree tree = parser.file();
        LoadCSVListener listener = new LoadCSVListener();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);
        System.out.println(listener.rows);
    }
}
package com.practice;

import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.*;


public class Col {
    public static void main(String args[]) throws Exception {
        String inputFile = null;
        if (args.length > 1)
            inputFile = args[1];
        InputStream is = System.in;
        if (inputFile != null)
            is = new FileInputStream(inputFile);
        CharStream input = CharStreams.fromStream(is);
        RowsLexer lexer = new RowsLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        int col = Integer.valueOf(args[0]);
        RowsParser parser = new RowsParser(tokens, col);
        parser.setBuildParseTree(false);
        parser.file();
    }
}

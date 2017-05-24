package com.practice;

import java.io.InputStream;
import java.io.FileInputStream;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * JsonXml
 */
public class JsonXml {
    public static void main(String[] args) throws Exception {
        String inputFile = null;
        if (args.length > 0)
            inputFile = args[0];
        InputStream is = System.in;
        if (inputFile != null) {
            is = new FileInputStream(inputFile);
        }
        CharStream input = CharStreams.fromStream(is);
        JSONLexer lexer = new JSONLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JSONParser parser = new JSONParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.json();
        ParseTreeWalker walker = new ParseTreeWalker();
        JsonXmlListener converter = new JsonXmlListener();
        walker.walk(converter, tree);
        System.out.println(converter.getXml(tree));

    }
}
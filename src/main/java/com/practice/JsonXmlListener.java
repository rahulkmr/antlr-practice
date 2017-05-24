package com.practice;

import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.ParseTree;

public class JsonXmlListener extends JSONBaseListener {
    ParseTreeProperty<String> xml = new ParseTreeProperty<String>();

    String getXml(ParseTree ctx) {
        return xml.get(ctx);
    }

    void setXml(ParseTree ctx, String s) {
        xml.put(ctx, s);
    }

    @Override
    public void exitJson(JSONParser.JsonContext ctx) {
        setXml(ctx, getXml(ctx.getChild(0)));
    }

    @Override
    public void exitAnObject(JSONParser.AnObjectContext ctx) {
        StringBuilder buf = new StringBuilder();
        buf.append("\n");
        for (JSONParser.PairContext pair_ctx : ctx.pair()) {
            buf.append(getXml(pair_ctx));
        }
        setXml(ctx, buf.toString());
    }

    @Override
    public void exitEmptyObject(JSONParser.EmptyObjectContext ctx) {
        setXml(ctx, "");
    }

    @Override
    public void exitAnArray(JSONParser.AnArrayContext ctx) {
        StringBuilder buf = new StringBuilder();
        buf.append("\n");
        for (JSONParser.ValueContext value_ctx : ctx.value()) {
            buf.append("<element>");
            buf.append(getXml(value_ctx));
            buf.append("</element>");
            buf.append("\n");
        }
        setXml(ctx, buf.toString());
    }

    @Override
    public void exitEmptyArray(JSONParser.EmptyArrayContext ctx) {
        setXml(ctx, "");
    }

    @Override
    public void exitPair(JSONParser.PairContext ctx) {
        String tag = stripQuotes(ctx.STRING().getText());
        JSONParser.ValueContext value_ctx = ctx.value();
        String val = String.format("<%s>%s</%s>\n", tag, getXml(value_ctx), tag);
        setXml(ctx, val);
    }

    @Override
    public void exitObjectValue(JSONParser.ObjectValueContext ctx) {
        setXml(ctx, getXml(ctx.object()));
    }

    @Override
    public void exitArrayValue(JSONParser.ArrayValueContext ctx) {
        setXml(ctx, getXml(ctx.array()));

    }

    @Override
    public void exitAtom(JSONParser.AtomContext ctx) {
        setXml(ctx, ctx.getText());
    }

    @Override
    public void exitString(JSONParser.StringContext ctx) {
        setXml(ctx, stripQuotes(ctx.getText()));
    }

    public static String stripQuotes(String s) {
        if (s == null || s.charAt(0) != '"')
            return s;
        return s.substring(1, s.length() - 1);
    }
}

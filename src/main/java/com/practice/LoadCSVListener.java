package com.practice;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;

public class LoadCSVListener extends CSVBaseListener {
    public static final String EMPTY = "";
    List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
    List<String> header;
    List<String> currentRowFieldValues;

    @Override
    public void exitHdr(CSVParser.HdrContext ctx) {
        header = new ArrayList<String>();
        header.addAll(currentRowFieldValues);
    }


    @Override
    public void enterRow(CSVParser.RowContext ctx) {
        currentRowFieldValues = new ArrayList<String>();
    }


    @Override
    public void exitRow(CSVParser.RowContext ctx) {
        if (header == null) {
            return;
        }
        Map<String, String> row = new LinkedHashMap<String, String>();
        int i = 0;
        for (String val: currentRowFieldValues) {
            row.put(header.get(i), val);
            i++;
        }
        rows.add(row);
    }

    @Override
    public void exitString(CSVParser.StringContext ctx) {
        currentRowFieldValues.add(ctx.STRING().getText());
    }

    @Override
    public void exitText(CSVParser.TextContext ctx) {
        currentRowFieldValues.add(ctx.TEXT().getText());
    }

    @Override
    public void exitEmpty(CSVParser.EmptyContext ctx) {
        currentRowFieldValues.add(EMPTY);
    }
} 
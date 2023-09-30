package dev.lueem.extract;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dev.lueem.model.Article;

public class ExtractPositionDetail {

    private static final Pattern PATTERN = Pattern
            .compile("^(.*?)(\\d+)(g|G|CL|ML|kg|x)\\s+(\\d+\\.\\d+)\\s+(\\d+\\.\\d+)$");

    public Article extractDetail(String extractedArticleLine) {
        return null;
        // Article position = new Article();
        // List<String> lines = Arrays.asList(extractedArticleLine.split(";"));

        // for (String line : lines) {
        // Matcher matcher = PATTERN.matcher(line);
        // if (matcher.find()) {
        // position.setArtikel(matcher.group(1).trim());
        // position.setEinheit(matcher.group(3));
        // try {
        // position.setPreis(Double.parseDouble(matcher.group(4)));
        // position.setTotal(Double.parseDouble(matcher.group(5)));
        // } catch (NumberFormatException nfe) {
        // System.err.println("Fehler in Zahlenfeldern bei der Zeile: " + line);
        // }
        // }
        // }
        // return position;
    }
}

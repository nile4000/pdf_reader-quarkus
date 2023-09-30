package dev.lueem.extract;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

public class ExtractCoopPosition {

    // Suchtext
    private static final String START_POSITION = "Artikel Menge Preis Aktion Total Zusatz";
    private static final String END_POSITION_TOTAL = "Total CHF ";
    private static final String END_POSITION_BON = "Bon ";
    private static final String PAGE_PATTERN = "Seite \\d+/\\d+";

    public JsonArray extractArticlesFromContent(String documentContent) {
        Pattern articlePattern = Pattern.compile(
                "^(.+?)\\s+(\\d+)\\s+(\\d+\\.\\d{2})(?:\\s+(\\d+\\.\\d{2}))?\\s+(\\d+\\.\\d{2})\\s+(\\S)?$",
                Pattern.MULTILINE);
        Matcher matcher = articlePattern.matcher(documentContent);

        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        while (matcher.find()) {
            String name = matcher.group(1).trim();
            int quantity = Integer.parseInt(matcher.group(2).trim());
            double price = Double.parseDouble(matcher.group(3).trim());
            double actionPrice = matcher.group(4) != null ? Double.parseDouble(matcher.group(4).trim()) : 0;
            double total = Double.parseDouble(matcher.group(5).trim());
            String additional = matcher.group(6) != null ? matcher.group(6).trim() : "";

            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("Name", name)
                    .add("Menge", quantity)
                    .add("Preis", price)
                    .add("Aktion", actionPrice)
                    .add("Total", total)
                    .add("Zusatz", additional)
                    .build();

            jsonArrayBuilder.add(jsonObject);
        }

        return jsonArrayBuilder.build();
    }

    public JsonArray extractArticleAsJson(LocalDate documentDate, String documentContent) {
        int startPositionIndex = documentContent.indexOf(START_POSITION);
        int endPositionBonIndex = documentContent.indexOf(END_POSITION_BON);
        int endPositionTotalIndex = documentContent.indexOf(END_POSITION_TOTAL);

        // Prüfen, ob der Start-Position-Index gefunden wurde
        if (startPositionIndex == -1) {
            return null;
        }

        String cuttedString;

        if (endPositionBonIndex != -1) {
            cuttedString = documentContent.substring(startPositionIndex, endPositionBonIndex);
        } else if (endPositionTotalIndex != -1) {
            cuttedString = documentContent.substring(startPositionIndex, endPositionTotalIndex);
        } else {
            return null;
        }

        // Unterteilen nach Whitespace, Umwandlung zu ArrayList
        List<String> lines = new ArrayList<>(Arrays.asList(cuttedString.split("\\r?\\n")));

        // Seitenzahlen entfernen
        ListIterator<String> iterator = lines.listIterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.trim().matches(PAGE_PATTERN)) {
                iterator.remove();
            }
        }

        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        // Begintext definieren, Dokumentedatum pro Position ausgeben
        int startIndex = lines.indexOf(START_POSITION);
        if (startIndex != -1 && startIndex + 1 < lines.size()) {
            for (String line : lines.subList(startIndex + 1, lines.size())) {
                JsonObject articleObj = Json.createObjectBuilder()
                        .add("Article", line)
                        .add("Date", documentDate.toString()) // Datum als String
                        .build();
                jsonArrayBuilder.add(articleObj);
            }
        }

        return jsonArrayBuilder.build();
    }

}

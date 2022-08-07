package dev.morling.quarkus.pdfextract.extract;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class ExtractCoopPosition {

    // Suchtext
    String startPosition = "Artikel Menge Preis Aktion Total Zusatz";
    String cuttedString = "";
    String endPositionTotal = "Total CHF ";
    String endPositionBon = "Bon ";
    String s = "";

    public String extractArticle(LocalDate documentDate, String documentContent) {

        // Start/Enden zuschneiden
        if (cuttedString.contains(endPositionBon)) {
            cuttedString = documentContent.substring(documentContent.indexOf(startPosition),
                    documentContent.indexOf(endPositionBon));
        }
        cuttedString = documentContent.substring(documentContent.indexOf(startPosition),
                documentContent.indexOf(endPositionTotal));

        // Unterteilen nach Whitespace, Umwandlung zu ArrayList
        List<String> lines = new ArrayList<>(Arrays.asList(cuttedString.split("\\r?\\n")));

        // Seitenzahlen entfernen
        ListIterator<String> iterator = lines.listIterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.trim().matches(
                    "Seite 1/2|Seite 2/2|Seite 1/3|Seite 2/3|Seite 3/3|Seite 1/4|Seite 2/4|Seite 3/4|Seite 4/4")) {
                // Delete element
                iterator.remove();
            }
            // Begintext definieren, Dokumentedatum pro Position ausgeben
            int startIndex = lines.indexOf(startPosition);
            s = String.join(documentDate + ";", lines.subList(startIndex + 1, lines.size()));

            return s;
        }
        return null;
    }
}

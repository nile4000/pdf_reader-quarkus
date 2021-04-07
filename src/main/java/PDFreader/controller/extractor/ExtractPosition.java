package PDFreader.controller.extractor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class ExtractPosition {

    // Suchtext
    String begin = "Artikel Menge Preis Aktion Total Zusatz";
    String end = "Total CHF ";
    String s = "";

    public String extractArticle(String line) {

        // begin definieren & endtext abschneiden
        String cuttedTextLine = line.substring(line.indexOf(begin), line.indexOf(end));

        // // Unterteilen nach Whitespace, Umwandlung zu ArrayList
        List<String> lines = new ArrayList<>(Arrays.asList(cuttedTextLine.split("\\r?\\n")));

        // Seitenzahlen entfernen
        ListIterator<String> iterator = lines.listIterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.trim().matches(
                    "Seite 1/2|Seite 2/2|Seite 1/3|Seite 2/3|Seite 3/3|Seite 1/4|Seite 2/4|Seite 3/4|Seite 4/4")) {

                // Delete element
                iterator.remove();
            }

            // Begintext definieren
            int positionBegin = lines.indexOf(begin);
            s = String.join(";", lines.subList(positionBegin + 1, lines.size()));

            System.out.println(s);

            return s;
        }
        return null;

    }

}

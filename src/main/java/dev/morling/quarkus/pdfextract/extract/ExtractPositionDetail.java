package dev.morling.quarkus.pdfextract.extract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dev.morling.quarkus.pdfextract.model.Position;

public class ExtractPositionDetail {

    public Position extractDetail(String extractedArticleLine) {

        // Unterteilen nach Whitespace, Umwandlung zu ArrayList
        Position position = new Position();
        String zahlen;
        List<String> lines = new ArrayList<>(Arrays.asList(extractedArticleLine.split(";")));

        // Einheiten erkennen g/G/CL/G/ML/kg
        Pattern pattern = Pattern.compile("(\\d+)g|(\\d+)G|(\\d+)CL|(\\d+)ML|(\\d+)kg|(\\d+)x");
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {

                // Bezeichnung entnehmen
                position.setBezeichnung(line.substring(0, matcher.start()).trim());

                // Zahlen entnehmen
                try {
                    zahlen = line.substring(matcher.end(), line.length()).trim();
                    String[] split = zahlen.split(" ");

                    // Position erstellen
                    position.setEinheit(split[0]);
                    position.setPreis(Double.parseDouble(split[1]));
                    position.setTotal(Double.parseDouble(split[2]));
                } catch (Exception e) {
                    System.err.println("Fehler in Zahlenfeldern");
                }
            }
        }

        return position;

    }

}

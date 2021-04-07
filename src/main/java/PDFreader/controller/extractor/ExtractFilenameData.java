package PDFreader.controller.extractor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class ExtractFilenameData {

  // Datum extrahieren aus String
  public LocalDate extractDate(String searchDate) throws ParseException {

    // Suchpattern
    Pattern p = Pattern.compile("\\d{4}\\d{2}\\d{2}");

    // Matcher für input String
    Matcher matcher = p.matcher(searchDate);

    if (matcher.find()) {

      // Umformatierung Datum
      LocalDate date1 = LocalDate.parse(matcher.group(), DateTimeFormatter.ofPattern("yyyyMMdd"));

      return date1;
    }
    return null;
  }

  // Ort extrahieren aus String
  public String extractLocation(String searchLocation) {

    // Zweiten Underscore suchen
    int indexUnderscore = searchLocation.indexOf("_", searchLocation.indexOf("_") + 1);

    // Ausgabe Text bis zum Underscore
    String ausgabe = searchLocation.substring(0, indexUnderscore);

    // Underscore ersetzen
    return ausgabe.replace("_", " ");
  }

}

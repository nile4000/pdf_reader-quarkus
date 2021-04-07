package PDFreader.controller.extractor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractPositionDetail {

    public String extractDetail(String extractedArticleLine) {

        // Unterteilen nach Whitespace, Umwandlung zu ArrayList
        List<String> lines = new ArrayList<String>(Arrays.asList(extractedArticleLine.split(";")));

        // Einheiten g/G/CL/G/ML/kg
        Pattern pattern = Pattern.compile("(\\d+)g|(\\d+)G|(\\d+)CL|(\\d+)ML|(\\d+)kg");
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                System.out.println(line.substring(0, matcher.start()) +","+ line.substring(matcher.end(), line.length()));
            }
        }

        return "";

    }

}

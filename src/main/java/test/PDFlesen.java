// import java.io.File;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.io.LineNumberReader;
// import java.util.Arrays;
// import java.util.List;

// import javax.json.bind.Jsonb;
// import javax.json.bind.JsonbBuilder;
// import org.apache.pdfbox.pdmodel.PDDocument;
// import org.apache.pdfbox.text.PDFTextStripper;
// import org.apache.pdfbox.text.PDFTextStripperByArea;

// public class PDFlesen {

//     //public static void main(String[] args) throws IOException {

//         // Attribute
//         String fileName = "Coop.pdf";
//         String start = "Artikel Menge Preis Aktion Total Zusatz";
//         String ende = "Total CHF 115.95";

//         try (PDDocument document = PDDocument.load(new File(fileName))) {

//             document.getClass();

//             if (!document.isEncrypted()) {

//                 PDFTextStripperByArea stripper = new PDFTextStripperByArea();
//                 stripper.setSortByPosition(true);
//                 stripper.beginText();
//                 // stripper.setStartBookmark(start);

//                 PDFTextStripper tStripper = new PDFTextStripper();

//                 String pdfFileInText = tStripper.getText(document);
//                 // pdfFileInText.setParagraphStart(start);
//                 // stripper.setParagraphEnd(ende);

//                 //Datum finden
                
//                 pdfFileInText.
//                 // split by whitespace
//                 String lines[] = pdfFileInText.split("\\r?\\n");

//                 // ArrayList erstellen
//                 List newLines = Arrays.asList(lines);
//                 int indexStart = newLines.indexOf(start);
//                 int indexEnde = newLines.indexOf(ende);

//                 for (String line : lines) {
//                     System.out.println(line);
//                 }

//                 //// Zeilennummern ausfindig machen
//                 // LineNumberReader lineNumberReader = new
//                 //// LineNumberReader(newLines.listIterator());

//                 // int data = lineNumberReader.read();
//                 // while (data != -1) {
//                 // char dataChar = (char) data;
//                 // data = lineNumberReader.read();
//                 // int lineNumber = lineNumberReader.getLineNumber();
//                 // }
//                 // lineNumberReader.close();

//                 //// Positionen ausgeben
//                 // System.out.println(indexStart + ", " + indexEnde);

//                 // Ausgabe

//                 // Arrays.asList(lines).contains(ende);

//                 // JSON-Builder erstellen
//                 Jsonb jsonb = JsonbBuilder.create();

//                 // Pattern regexp = Pattern.compile();

//                 // JSON-Datei aus Objekt
//                 // try (FileWriter fileWriter = new FileWriter("Quittung.json")) {
//                 // jsonb.toJson(lines, fileWriter);
//                 // System.out.println("JSON-Datei erstellt");

//             }

//         }

//     }

// }

package PDFreader.controller.generator;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import PDFreader.controller.extractor.ExtractFilenameData;
import PDFreader.controller.extractor.ExtractPosition;
import PDFreader.controller.extractor.ExtractPositionDetail;
import PDFreader.controller.extractor.ExtractText;
import PDFreader.controller.extractor.FolderSearch;
import PDFreader.modell.Beleg;

public class BelegdatenGenerator {

    // Attribute
    List<String> listFolder;
    List<String> listArticles;
    Beleg beleg;

    // Instanzierung
    FolderSearch folderSearch = new FolderSearch();
    ExtractFilenameData extractionDate = new ExtractFilenameData();
    ExtractFilenameData extractionLocation = new ExtractFilenameData();
    ExtractPosition extractionArticle = new ExtractPosition();
    ExtractPositionDetail extractionArticleDetail = new ExtractPositionDetail();
    ExtractText reader = new ExtractText();

    // Konstruktor
    public BelegdatenGenerator() {
        super();
    }

    // Methoden
    public Beleg belegGenerieren() throws ParseException, IOException {

        listFolder = folderSearch.verzeichnisDurchsuchen();

        for (String sF : listFolder) {

            for (String sA : listArticles) {

                sA = extractionArticleDetail
                        .extractDetail(extractionArticle.extractArticle(reader.pdfEinlesen("Kassenzettel/" + sF)));
            }

            beleg = new Beleg(extractionDate.extractDate(sF), extractionLocation.extractLocation(sF), null);
            System.out.println(beleg.getDateReceipt() + " " + beleg.getLocation());

        }

        return beleg;

    };

}

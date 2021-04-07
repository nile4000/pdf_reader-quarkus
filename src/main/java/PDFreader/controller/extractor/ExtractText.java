package PDFreader.controller.extractor;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class ExtractText {

    public String pdfEinlesen(String fileName) throws IOException {

        String pdfFileInText = null;

        try (PDDocument document = PDDocument.load(new File(fileName))) {

            document.getClass();

            if (!document.isEncrypted()) {

                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();

                pdfFileInText = tStripper.getText(document);

                return pdfFileInText;

            }

        }
        return null;
    }
}
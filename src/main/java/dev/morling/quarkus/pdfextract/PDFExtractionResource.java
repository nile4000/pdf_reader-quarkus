/*
 * Copyright Gunnar Morling.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package dev.morling.quarkus.pdfextract;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.lucene.document.Document;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import dev.morling.quarkus.pdfextract.extract.ExtractCoopPosition;
import dev.morling.quarkus.pdfextract.extract.ExtractPositionDetail;
import dev.morling.quarkus.pdfextract.extract.PDFLayoutTextStripper;
import dev.morling.quarkus.pdfextract.index.LucenePDFDocument;
import dev.morling.quarkus.pdfextract.model.Beleg;
import dev.morling.quarkus.pdfextract.model.Position;
import dev.morling.quarkus.pdfextract.writer.JSONFileWriter;

@Path("/rest")
public class PDFExtractionResource {

    LucenePDFDocument lDocument = new LucenePDFDocument();
    ExtractCoopPosition extractLine = new ExtractCoopPosition();
    ExtractPositionDetail articleDetail = new ExtractPositionDetail();
    JSONFileWriter jsonFileWriter = new JSONFileWriter();

    @POST
    @Path("/extract")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response extractTextFromPdf(@MultipartForm FormData formData) throws BadRequestException {
        try {
            if (formData.getPdfFile() == null || formData.getPdfFile().length() == 0) {
                ResponseBuilder response = Response.status(Status.BAD_REQUEST);
                response.header("Reason", "No PDF file was uploaded (use form parameter 'pdfFile')");
                return response.build();
            }
            // Textextraktion mit PDFBox
            String text = getText(formData.getPdfFile());

            // Dokumentedatumextraktion aus Dokumenten-Metadaten
            PDDocument doc = PDDocument.load(formData.getPdfFile());
            PDDocumentInformation metadata = doc.getDocumentInformation();
            Calendar creationDate = metadata.getCreationDate();
            LocalDate localDate = LocalDateTime
                    .ofInstant(creationDate.toInstant(), creationDate.getTimeZone().toZoneId()).toLocalDate();

            File temp = File.createTempFile("tempfile", ".tmp");

            try (PrintStream out = new PrintStream(new FileOutputStream(temp))) {
                out.print(text);

            }
            ResponseBuilder response = Response.ok(temp);
            response.header("Content-Disposition", "attachment;filename=" + localDate + "_content_extracted.txt");
            return response.build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/article")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response extractArticlesFromPdf(@MultipartForm FormData formData) throws BadRequestException {
        try {
            if (formData.getPdfFile() == null || formData.getPdfFile().length() == 0) {
                ResponseBuilder response = Response.status(Status.BAD_REQUEST);
                response.header("Reason", "No PDF file was uploaded (use form parameter 'pdfFile')");
                return response.build();
            }

            // Textextraktion manuell / indexierung mit Lucene
            Document doc = lDocument.convertDocument(formData.getPdfFile());
            doc = LucenePDFDocument.getDocument(formData.getPdfFile());

            // Daten aus Unstrukturiertem Dokumenteinhalt extrahieren-formatieren
            String documentContent = doc.getField("summary").stringValue();
            String in = doc.getField("CreationDate").stringValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.ENGLISH);
            LocalDate dateTime = LocalDate.parse(in, formatter);

            // Listen initialisieren
            List<Position> test = new ArrayList<>();

            List<Position> positions = Arrays
                    .asList(articleDetail.extractDetail(extractLine.extractArticle(dateTime, documentContent)));

            JsonArrayBuilder builder = Json.createArrayBuilder();

            // Get position
            for (Position pos : positions) {

                JsonObject jsonPositions = Json.createObjectBuilder()
                        .add("Datum", in)
                        .add("Bezeichnung", pos.getBezeichnung())
                        .add("Preis", pos.getPreis())
                        .add("Einheit", pos.getEinheit())
                        .add("Total", pos.getTotal()).build();
                builder.add(jsonPositions);
            }
            JsonArray jsonArray = builder.build();

            File temp = File.createTempFile("tempfile", ".tmp");

            try (PrintStream out = new PrintStream(new FileOutputStream(temp))) {

                for (int i = 0; i < jsonArray.size(); i++) {
                    out.print(jsonArray.getJsonObject(i));
                }
            }
            ResponseBuilder response = Response.ok(temp);
            // Generate JSON-file
            response.header("Content-Disposition", "attachment;filename=" + dateTime + "_articles_extracted.json");
            return response.build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String getText(File pdfFile) throws IOException {
        PDDocument doc = null;
        try {
            doc = PDDocument.load(pdfFile);

            // Dokumenteninhalt wird übergeben, Textformatierung & Layout erstellung
            return new PDFLayoutTextStripper().getText(doc);
        } finally {
            if (doc != null) {
                doc.close();
            }
        }
    }
}
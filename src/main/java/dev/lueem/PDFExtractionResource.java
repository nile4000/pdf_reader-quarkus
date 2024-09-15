package dev.lueem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.stream.JsonParsingException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import dev.lueem.ai.OpenAiClient;
import dev.lueem.extract.PDFLayoutTextStripper;

@Path("/rest")
public class PDFExtractionResource {

    private static final String NO_PDF_UPLOADED_MSG = "No PDF file was uploaded (use form parameter 'pdfFile')";

    @POST
    @Path("/article")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response extractArticlesFromPdf(@MultipartForm FormData formData) {
        if (formData.getPdfFile() == null) {
            return createBadRequestResponse(NO_PDF_UPLOADED_MSG);
        }

        File pdfFile = convertInputStreamToFile(formData.getPdfFile());
        String documentContent = extractTextFromPdf(pdfFile);
        String cleanedContent = cleanContent(documentContent);
        String receiptContent = extractArticlesUntilTotal(cleanedContent);

        OpenAiClient openAiClient = new OpenAiClient();
        String question = "Extract articles and return a list in a valid JSON format: name, price, quantity, discount (or 0 if none) from the given receipt.\n"
                + receiptContent;
        String answer = openAiClient.askQuestion(question);

        return buildResponse(answer);
    }

    private Response createBadRequestResponse(String reason) {
        return Response.status(Status.BAD_REQUEST)
                .header("Reason", reason)
                .build();
    }

    private File convertInputStreamToFile(InputStream inputStream) {
        try {
            File tempFile = Files.createTempFile("uploadedPdf", ".pdf").toFile();
            try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
                inputStream.transferTo(outputStream);
            }
            return tempFile;
        } catch (IOException e) {
            throw new BadRequestException("Failed to process the uploaded file", e);
        }
    }

    private String extractTextFromPdf(File pdfFile) {
        try (PDDocument document = Loader.loadPDF(pdfFile)) {
            return new PDFLayoutTextStripper().getText(document);
        } catch (IOException e) {
            throw new BadRequestException("Failed to extract text from PDF", e);
        }
    }

    private String cleanContent(String content) {
        return content.replaceAll(" +", " ")
                .replaceAll("(?m)^\\s+$", "")
                .replaceAll("\n+", "\n")
                .trim();
    }

    private String extractArticlesUntilTotal(String content) {
        int indexOfTotal = content.indexOf("Total CHF");
        if (indexOfTotal != -1) {
            return content.substring(0, indexOfTotal).trim();
        }
        return content;
    }

    private Response buildResponse(String answer) {
        try (JsonReader jsonReader = Json.createReader(new StringReader(answer))) {
            JsonObject answerJson = jsonReader.readObject();
            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("answer", answerJson)
                    .build();
            return Response.ok(jsonResponse)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON + ";charset=UTF-8")
                    .build();
        } catch (JsonParsingException e) {
            // Wenn das Parsing fehlschlägt, die ursprüngliche Antwort als Text zurückgeben
            return Response.ok(answer)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN + ";charset=UTF-8")
                    .build();
        }
    }
}

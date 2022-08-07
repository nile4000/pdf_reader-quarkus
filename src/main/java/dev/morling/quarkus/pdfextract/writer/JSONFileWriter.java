package dev.morling.quarkus.pdfextract.writer;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.FileWriter;

public class JSONFileWriter {

    /**
     * a method to easily write a json file
     *
     * @param fileName Name of your desired file
     * @param jsonObject the Object you want to write in the file
     */
    public void writeJSONFile(String fileName, JsonObject jsonObject) throws Exception {
        try (FileWriter newFileWriter = new FileWriter(fileName)) {
            Json.createWriter(newFileWriter).writeObject(jsonObject);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}

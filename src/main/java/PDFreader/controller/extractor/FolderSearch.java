package PDFreader.controller.extractor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FolderSearch {

  // Attribute
  String verzeichnis = null;

  // Methoden
  public List<String> verzeichnisDurchsuchen() {

    List<String> fileNames = new ArrayList<String>();

    // If this pathname does not denote a directory, then listFiles() returns null.
    File[] files = new File("Kassenzettel").listFiles();

    for (File file : files) {
      if (file.isFile()) {

        // Aufgabe Filename + Zeilenumbruch
        fileNames.add(file.getName());

      }

    }
    return fileNames;
  }

}
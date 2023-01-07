# quarkus-pdf-extract Project #

### Thema json-b ###

### Ziele json-b ###

- Aus mehreren PDF-Dokumenten eine JSON-Dateien mit JSON-B zu erstellen
- Erweiterte Aufteilung des PDF-Dokuments
#### Vorgehen json-b ####

- Betriebstest
- Json-B kennen lernen
- Umbau FileUpload - diverse Tests FileUploadService
- Recherche Multipart Data
- JSON-Filewriterkomponente bauen
- ExtractDetail Funktion bauen
- Beleg / Position Klassen erstellen
- JSON-Mapping Tests
- JSON-Mapping erweitern
- JSON-File Erstellung und Test
- Test ArrayList zu JSON fehlgeschlagen (nur ein Artikel wird eingelesen)

### Thema pdfbox ###

- Dokumente in andere Formate umwandeln
- Erstellen von PDFs und Druckerzeugnissen

### Ziele pdfbox ###

- Einen Endpunkt erstellen an den PDF-Dokumente gesendet werden können.
- Aus dem PDF sollen einzelne Zeilen extrahiert und umgewandelt werden.
- Das PDF-Dokument soll nach gewissen mustern aufgeteilt werden (Artikel Menge Preis Aktion Total Zusatz Datum;)

#### Vorgehen pdfbox ####

- Betrieb der Applikation (Quarkus)
- „GUI“-Anpassung "index.html"
- In der Klasse ExtractCoopPosition könnnen Positionen aus den PDF-Dateien im Ordner "PDF_Documents" extrahiert und zusammengestellt werden
- PDF-Dokument lesen & drucken im .txt-Format mit PDFBox (PDFLayoutTextStripper)
- Dokumente indexieren, unstrukturierte Daten durchsuchbar machen mit dem Einsatz von Lucene "..\pdfextract\index"
- "Metadata" extrahiert mit PDFBox (PDFExtractionresource)

## Lokaler Start ##

./mvnw compile quarkus:dev

## Open UI ##

<http://localhost:8080/>

-> Datei auswählen im Ordner "/PDF_Documents"
-> Gesamte Dateistruktur umwandeln mit Button "Einlesen (alles)"
-> Artikel extrahieren mit Button "Einlesen (Coop-Artikel)"

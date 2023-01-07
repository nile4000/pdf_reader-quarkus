# quarkus-pdf-extract Project #

### Thema pdfbox ###

- Dokumente in andere Formate umwandeln
- Erstellen von PDFs und Druckerzeugnissen

### Ziele pdfbox ###

- Einen Endpunkt erstellen an den PDF-Dokumente gesendet werden können.
- Aus dem PDF sollen einzelne Zeilen extrahiert und umgewandelt werden.
- Das PDF-Dokument soll nach gewissen mustern aufgeteilt werden (Artikel Menge Preis Aktion Total Zusatz Datum;)

#### Dokumentation pdfReader ####

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
-> Gesamten Text umwandeln in .txt mit Button "Einlesen (alles)"
-> Einzelne Artikel extrahieren mit Button "Einlesen (Coop-Artikel)"

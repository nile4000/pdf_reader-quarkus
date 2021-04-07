// import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
// import org.apache.poi.ss.usermodel.*;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.Calendar;
// import java.util.List;

// public class ExcelWriter {

//     private static String[] columns = { "Datum", "Bezeichnung", "Einheit", "Preis", "Aktion", "Total", "Zusatz" };
//     private static List<Position> articles = new ArrayList<>();

//     // Initializing Articles data to insert into the excel file
//     static {
//         Calendar dateReceipt = Calendar.getInstance();
//         dateReceipt.set(1992, 7, 21);
//         //articles.add(new Position(dateReceipt.getTime(), "Karotten", "1ST", 10.0, 0, 10.0, ""));

//     }

//     public void generateExcel() throws IOException, InvalidFormatException {
//         // Create a Workbook
//         Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

//         CreationHelper createHelper = workbook.getCreationHelper();

//         // Create a Sheet
//         Sheet sheet = workbook.createSheet("Article");

//         // Create a Font for styling header cells
//         Font headerFont = workbook.createFont();
//         headerFont.setBold(true);
//         headerFont.setFontHeightInPoints((short) 14);
//         headerFont.setColor(IndexedColors.RED.getIndex());

//         // Create a CellStyle with the font
//         CellStyle headerCellStyle = workbook.createCellStyle();
//         headerCellStyle.setFont(headerFont);

//         // Create a Row
//         Row headerRow = sheet.createRow(0);

//         // Create cells
//         for (int i = 0; i < columns.length; i++) {
//             Cell cell = headerRow.createCell(i);
//             cell.setCellValue(columns[i]);
//             cell.setCellStyle(headerCellStyle);
//         }

//         // Create Cell Style for formatting Date
//         CellStyle dateCellStyle = workbook.createCellStyle();
//         dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd.MM.yyyy"));

//         // Create Other rows and cells with Articles data
//         int rowNum = 1;
//         for (Position Article : articles) {
//             Row row = sheet.createRow(rowNum++);

//             Cell dateReceiptCell = row.createCell(0);
//             dateReceiptCell.setCellValue(Article.getdateReceipt());
//             dateReceiptCell.setCellStyle(dateCellStyle);

//             row.createCell(1).setCellValue(Article.getBezeichnung());

//             row.createCell(2).setCellValue(Article.getEinheit());

//             row.createCell(3).setCellValue(Article.getPreis());

//             row.createCell(4).setCellValue(Article.getAktion());

//             row.createCell(5).setCellValue(Article.getTotal());

//             row.createCell(6).setCellValue(Article.getZusatz());
//         }

//         // Resize all columns to fit the content size
//         for (int i = 0; i < columns.length; i++) {
//             sheet.autoSizeColumn(i);
//         }

//         // Write the output to a file
//         FileOutputStream fileOut = new FileOutputStream("ausgabeFile.xlsx");
//         workbook.write(fileOut);
//         fileOut.close();

//         // Closing the workbook
//         workbook.close();
//     }
// }

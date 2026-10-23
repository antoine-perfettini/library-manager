package org.library_manager.utilitary;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PDFExporter {

    public static void exportTableToPDF(JTable pTable, String pFilePath) {
        try {
            Document vDocument = new Document(PageSize.A4);
            PdfWriter.getInstance(vDocument, new FileOutputStream(pFilePath));
            vDocument.open();

            vDocument.add(new Paragraph("Library Data Export"));
            vDocument.add(new Paragraph(" "));

            PdfPTable vPdfTable = new PdfPTable(pTable.getColumnCount());

            for (int i = 0; i < pTable.getColumnCount(); i++) {
                vPdfTable.addCell(pTable.getColumnName(i));
            }

            for (int rows = 0; rows < pTable.getRowCount(); rows++) {
                for (int cols = 0; cols < pTable.getColumnCount(); cols++) {
                    Object value = pTable.getValueAt(rows, cols);
                    String cellValue = (value != null) ? value.toString() : "";
                    vPdfTable.addCell(cellValue);
                }
            }

            vDocument.add(vPdfTable);
            vDocument.close();

            JOptionPane.showMessageDialog(null, "PDF exportation was a success !\n" + pFilePath);

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error during PDF exportation : " + e.getMessage());
        }
    }
}

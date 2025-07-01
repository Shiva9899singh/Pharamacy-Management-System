package com.pharmacy.sales.payment.util;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.pharmacy.sales.payment.entity.Sale;

import java.io.OutputStream;
import java.util.List;

public class PdfReportGenerator {
    public static void generateSalesReport(List<Sale> sales, OutputStream out) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, out);
        document.open();

        document.add(new Paragraph("Sales Report"));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(4);
        table.addCell("ID");
        table.addCell("Drug ID");
        table.addCell("Quantity Sold");
        table.addCell("Date");

        for (Sale sale : sales) {
            table.addCell(String.valueOf(sale.getId()));
            table.addCell(String.valueOf(sale.getDrugId()));
            table.addCell(String.valueOf(sale.getQuantitySold()));
            table.addCell(sale.getDate().toString());
        }

        document.add(table);
        document.close();
    }
}
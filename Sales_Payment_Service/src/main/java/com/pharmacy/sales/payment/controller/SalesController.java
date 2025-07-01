package com.pharmacy.sales.payment.controller;
import org.springframework.web.bind.annotation.*;

import com.pharmacy.sales.payment.entity.Sale;
import com.pharmacy.sales.payment.service.SaleService;
import com.pharmacy.sales.payment.util.PdfReportGenerator;

import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {
    private final SaleService saleService;

    public SalesController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/report")
    public List<Sale> getSalesReport() {
        return saleService.getAllSales();
    }

    @GetMapping("/download")
    public void downloadSalesReport(HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=sales_report.pdf");
            List<Sale> sales = saleService.getAllSales();
            PdfReportGenerator.generateSalesReport(sales, response.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}
package com.pharmacy.sales.payment.service;
import org.springframework.stereotype.Service;

import com.pharmacy.sales.payment.entity.Sale;
import com.pharmacy.sales.payment.repository.SaleRepository;

import java.util.List;

@Service
public class SaleService {
    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }
}
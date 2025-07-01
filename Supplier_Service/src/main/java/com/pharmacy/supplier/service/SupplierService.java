package com.pharmacy.supplier.service;

import java.util.List;

import com.pharmacy.supplier.entity.Supplier;

public interface SupplierService {

    List<Supplier> getAllSuppliers();

    Supplier getSupplierById(Long id);

    Supplier createSupplier(Supplier supplier);

    Supplier updateSupplier(Long id, Supplier supplierDetails);

    void deleteSupplier(Long id);
    
    Supplier getSupplierByName(String name);
}

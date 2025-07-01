package com.pharmacy.supplier.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.supplier.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
	Optional<Supplier> findByName(String name);
}

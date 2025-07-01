package com.pharmacy.sales.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.sales.payment.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>{

}

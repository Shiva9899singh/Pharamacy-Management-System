package com.pharmacy.sales.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.sales.payment.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>  {

}

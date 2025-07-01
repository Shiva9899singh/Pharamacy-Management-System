package com.pharmacy.sales.payment.service;
import org.springframework.stereotype.Service;

import com.pharmacy.sales.payment.entity.Payment;
import com.pharmacy.sales.payment.repository.PaymentRepository;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }
}
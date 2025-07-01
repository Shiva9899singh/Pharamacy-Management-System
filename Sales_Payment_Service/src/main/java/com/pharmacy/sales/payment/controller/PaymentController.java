package com.pharmacy.sales.payment.controller;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.pharmacy.sales.payment.entity.Payment;
import com.pharmacy.sales.payment.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Payment> makePayment(@RequestBody Payment payment) {
        Payment saved = paymentService.savePayment(payment);
        return ResponseEntity.ok(saved);
    }
}

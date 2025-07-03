package com.pharmacy.NotificationService.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmacy.NotificationService.dto.OrderEvent;
import com.pharmacy.NotificationService.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "order-placed-topic", groupId = "notification-group")
    public void consume(String message) {
        try {
            OrderEvent event = objectMapper.readValue(message, OrderEvent.class);
            emailService.sendOrderEmail(
                    event.getEmail(),
                    event.getMessage(),
                    "Pharmacy Order Confirmation"
            );
            System.out.println("\uD83D\uDCE7 Email sent to: " + event.getEmail());
        } catch (Exception e) {
            System.out.println("\u274C Error parsing or sending email: " + e.getMessage());
        }
    }
}

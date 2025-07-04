package com.pharmacy.NotificationService.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmacy.NotificationService.dto.OrderPlacedEvent;
import com.pharmacy.NotificationService.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderNotificationListener {

    @Autowired
    private EmailService emailService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "order-placed", groupId = "notification-group")
    public void consumeOrderEvent(String message) {
        try {
            OrderPlacedEvent event = objectMapper.readValue(message, OrderPlacedEvent.class);
            emailService.sendEmail(
                event.getEmail(),
                "Order Confirmation - ID " + event.getOrderId(),
                event.getMessage()
            );
            System.out.println("Notification sent for order ID: " + event.getOrderId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

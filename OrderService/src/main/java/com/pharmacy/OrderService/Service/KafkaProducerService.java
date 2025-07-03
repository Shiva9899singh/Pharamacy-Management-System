package com.pharmacy.OrderService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.pharmacy.OrderService.Dto.UserResponse;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private final String TOPIC = "order-placed-topic";

    public void sendOrderEvent(UserResponse event) {
        kafkaTemplate.send(TOPIC, event);
        System.out.println("✅ Kafka event sent: " + event);
    }
}

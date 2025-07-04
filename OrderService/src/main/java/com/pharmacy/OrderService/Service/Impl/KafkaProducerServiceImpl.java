package com.pharmacy.OrderService.Service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmacy.OrderService.Dto.OrderPlacedEvent;
import com.pharmacy.OrderService.Service.KafkaProducerService;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic.order}")
    private String topic;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void sendOrderPlacedEvent(OrderPlacedEvent event) {
        try {
            String json = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(topic, json);
            System.out.println("Published Kafka message: " + json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize OrderPlacedEvent to JSON", e);
        }
    }
}

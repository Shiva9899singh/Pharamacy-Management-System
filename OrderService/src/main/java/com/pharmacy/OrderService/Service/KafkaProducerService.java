package com.pharmacy.OrderService.Service;

import com.pharmacy.OrderService.Dto.OrderPlacedEvent;

public interface KafkaProducerService {
    void sendOrderPlacedEvent(OrderPlacedEvent event);
}

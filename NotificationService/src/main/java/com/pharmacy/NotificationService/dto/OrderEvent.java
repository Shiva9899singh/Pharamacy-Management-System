package com.pharmacy.NotificationService.dto;

import lombok.Data;

@Data
public class OrderEvent {
    private Long orderId;
    private String email;
    private String message;
}
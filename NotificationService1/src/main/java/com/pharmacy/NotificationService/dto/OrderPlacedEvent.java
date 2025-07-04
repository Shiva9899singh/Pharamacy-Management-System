package com.pharmacy.NotificationService.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderPlacedEvent {
    private String email;
    private Long orderId;
    private String message;
}
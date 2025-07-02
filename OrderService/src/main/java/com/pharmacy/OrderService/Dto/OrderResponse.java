package com.pharmacy.OrderService.Dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private long orderId;
    private String userId;
    private LocalDateTime orderDate;
    private double totalAmount;
    private List<OrderItemResponse> orderItems;
}
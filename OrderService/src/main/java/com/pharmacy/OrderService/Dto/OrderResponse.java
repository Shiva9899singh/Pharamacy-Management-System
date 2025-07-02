package com.pharmacy.OrderService.Dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderResponse {
    private Long orderId;
    private boolean verified;
    private boolean pickedUp;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> orderItems;
}

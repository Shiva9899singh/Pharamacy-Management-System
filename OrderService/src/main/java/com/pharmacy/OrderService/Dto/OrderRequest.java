// dto/OrderRequest.java
package com.pharmacy.OrderService.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderRequest {
	private String email;
    private List<OrderItemRequest> items;
}

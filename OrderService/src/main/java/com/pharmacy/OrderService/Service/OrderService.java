package com.pharmacy.OrderService.Service;

import java.util.List;

import com.pharmacy.OrderService.Dto.OrderRequest;
import com.pharmacy.OrderService.Dto.OrderResponse;

public interface OrderService {
   // OrderResponse placeOrder(Order order);
    List<OrderResponse> getAllOrders();
    OrderResponse verifyOrder(Long id);
    OrderResponse markOrderAsPickedUp(Long id);
	OrderResponse getOrderById(Long id);
	OrderResponse placeOrder(OrderRequest request, String username);
	
	
}

package com.pharmacy.OrderService.Service;

import com.pharmacy.OrderService.Dto.OrderResponse;
import com.pharmacy.OrderService.Entity.Order;
import java.util.List;

public interface OrderService {
    OrderResponse placeOrder(Order order);
    List<OrderResponse> getAllOrders();
    OrderResponse verifyOrder(Long id);
    OrderResponse markPickedUp(Long id);
	OrderResponse getOrderById(Long id);
	
	
}

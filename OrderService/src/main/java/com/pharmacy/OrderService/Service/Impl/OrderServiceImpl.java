package com.pharmacy.OrderService.Service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.OrderService.Dto.OrderItemResponse;
import com.pharmacy.OrderService.Dto.OrderResponse;
import com.pharmacy.OrderService.Entity.Order;
import com.pharmacy.OrderService.Exception.ResourceNotFoundException;
import com.pharmacy.OrderService.Repository.OrderRepository;
import com.pharmacy.OrderService.Service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private OrderResponse mapToResponse(Order order) {
        List<OrderItemResponse> items = order.getOrderItems().stream().map(item ->
                OrderItemResponse.builder()
                        .orderItemId(item.getOrderItemId())
                        .productId(item.getProductId())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .build()
        ).collect(Collectors.toList());

        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .userId(order.getUserId())
                .orderDate(order.getOrderDate())
                .totalAmount(order.getTotalAmount())
                .orderItems(items)
                .build();
    }

    @Override
    public OrderResponse placeOrder(Order order) {
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(
                order.getOrderItems().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum()
        );
        Order savedOrder = orderRepository.save(order);
        return mapToResponse(savedOrder);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public OrderResponse verifyOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));
        order.setUserId(order.getUserId() + "-VERIFIED"); // simulate verification update
        return mapToResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse markPickedUp(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));
        order.setUserId(order.getUserId() + "-PICKED_UP"); // simulate pickup update
        return mapToResponse(orderRepository.save(order));
    }
    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));

        return mapToResponse(order);
    }

}

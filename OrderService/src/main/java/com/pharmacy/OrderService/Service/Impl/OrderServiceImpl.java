package com.pharmacy.OrderService.Service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.pharmacy.OrderService.Dto.DrugResponse;
import com.pharmacy.OrderService.Dto.OrderItemResponse;
import com.pharmacy.OrderService.Dto.OrderPlacedEvent;
import com.pharmacy.OrderService.Dto.OrderRequest;
import com.pharmacy.OrderService.Dto.OrderResponse;
import com.pharmacy.OrderService.Entity.Order;
import com.pharmacy.OrderService.Entity.OrderItem;
import com.pharmacy.OrderService.Exception.OrderException;
import com.pharmacy.OrderService.Repository.OrderRepository;
import com.pharmacy.OrderService.Service.KafkaProducerService;
import com.pharmacy.OrderService.Service.OrderService;
import com.pharmacy.OrderService.client.DrugInventoryClient;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DrugInventoryClient drugClient;

//    @Autowired
//    private KafkaProducerService kafkaProducerService;
//
//    @Autowired
//    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${spring.kafka.topic.order}")
    private String orderTopic;

    private OrderResponse mapToResponse(Order order) {
        List<OrderItemResponse> items = order.getItems().stream().map(item ->
                OrderItemResponse.builder()
                        .orderItemId(item.getOrderItemId())
                        .productId(item.getProductId())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .build()).collect(Collectors.toList());

        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .verified(order.isVerified())
                .pickedUp(order.isPickedUp())
                .createdAt(order.getCreatedAt())
                .orderItems(items)
                .build();
    }

    @Override
    public OrderResponse placeOrder(OrderRequest request, String ignoredUsername) {
        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new OrderException("Order must contain at least one item.");
        }

        String email = request.getEmail(); // ✅ Taking email from request

        // Prepare Order Items and validate inventory
        List<OrderItem> orderItems = request.getItems().stream().map(item -> {
            DrugResponse drug = drugClient.getDrugByName(item.getProductId());

            if (drug.getQuantity() < item.getQuantity()) {
                throw new OrderException("Insufficient quantity for drug: " + item.getProductId());
            }

            // Update stock
            drugClient.updateDrugQuantity(item.getProductId(), item.getQuantity());

            return OrderItem.builder()
                    .productId(item.getProductId())
                    .quantity(item.getQuantity())
                    .price(drug.getPrice())
                    .build();
        }).collect(Collectors.toList());

        // Create Order
        Order order = Order.builder()
                .createdAt(LocalDateTime.now())
                .verified(false)
                .pickedUp(false)
                .email(email)
                .items(orderItems)
                .build();

        orderItems.forEach(item -> item.setOrder(order));

        Order savedOrder = orderRepository.save(order);

        // Send Kafka Event
//        OrderPlacedEvent event = OrderPlacedEvent.builder()
//                .email(email)
//                .orderId(savedOrder.getOrderId())
//                .message("Hi, your order (ID: " + savedOrder.getOrderId() + ") was placed successfully!")
//                .build();
//
//        kafkaProducerService.sendOrderPlacedEvent(event);
//
        return mapToResponse(savedOrder);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public OrderResponse verifyOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException("Order not found with id: " + orderId));
        order.setVerified(true);
        return mapToResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse markOrderAsPickedUp(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException("Order not found with id: " + orderId));
        order.setPickedUp(true);
        return mapToResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderException("Order not found with ID: " + id));
        return mapToResponse(order);
    }
}

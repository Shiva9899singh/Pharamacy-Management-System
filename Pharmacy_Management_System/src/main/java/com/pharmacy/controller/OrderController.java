package com.pharmacy.controller;


import com.pharmacy.model.Order;
import com.pharmacy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.placeOrder(order));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/{id}/verify")
    public ResponseEntity<Order> verifyOrder(@PathVariable String id) {
        Optional<Order> order = orderService.verifyOrder(id);
        return order.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/pickup")
    public ResponseEntity<Order> pickupOrder(@PathVariable String id) {
        Optional<Order> order = orderService.markAsPickedUp(id);
        return order.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}  

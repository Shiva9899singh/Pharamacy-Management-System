package com.pharmacy.OrderService.Controller;

import com.pharmacy.OrderService.Dto.OrderResponse;
import com.pharmacy.OrderService.Entity.Order;
import com.pharmacy.OrderService.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeorder")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.placeOrder(order));
    }
    @PostMapping("/bulk")
    public ResponseEntity<List<OrderResponse>> placeMultipleOrders(@RequestBody List<Order> orders) {
        List<OrderResponse> responses = orders.stream()
            .map(orderService::placeOrder)
            .toList();
        return ResponseEntity.ok(responses);
    }


    @GetMapping("/allorders")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        OrderResponse order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/verify/{id}")
    public ResponseEntity<OrderResponse> verifyOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.verifyOrder(id));
    }

    @PutMapping("/pickedup/{id}")
    public ResponseEntity<OrderResponse> markPickedUp(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.markPickedUp(id));
    }
}
package com.pharmacy.OrderService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.OrderService.Dto.OrderRequest;
import com.pharmacy.OrderService.Dto.OrderResponse;
import com.pharmacy.OrderService.Service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
//    @Autowired
//    private UserClient userClient;

    @PostMapping("/place")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest request) {
        OrderResponse response = orderService.placeOrder(request, null); // Username no longer needed
        return ResponseEntity.ok(response);
    }


    @GetMapping("/allorders")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PutMapping("/verify/{id}")
    public ResponseEntity<OrderResponse> verifyOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.verifyOrder(id));
    }

    @PutMapping("/pickup/{id}")
    public ResponseEntity<OrderResponse> markOrderAsPickedUp(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.markOrderAsPickedUp(id));
    }
}

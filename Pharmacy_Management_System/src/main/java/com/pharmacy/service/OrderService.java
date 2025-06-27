package com.pharmacy.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.model.Order;
import com.pharmacy.repository.OrderRepository;

@Service
public class OrderService {
	 @Autowired
	    private OrderRepository orderRepository;

	    public Order placeOrder(Order order) {
	        order.setStatus("PENDING");
	        return orderRepository.save(order);
	    }

	    public List<Order> getAllOrders() {
	        return orderRepository.findAll();
	    }

	    public Optional<Order> verifyOrder(String id) {
	        Optional<Order> order = orderRepository.findById(id);
	        order.ifPresent(o -> {
	            o.setStatus("VERIFIED");
	            orderRepository.save(o);
	        });
	        return order;
	    }

	    public Optional<Order> markAsPickedUp(String id) {
	        Optional<Order> order = orderRepository.findById(id);
	        order.ifPresent(o -> {
	            o.setStatus("PICKED_UP");
	            orderRepository.save(o);
	        });
	        return order;
	    }


}

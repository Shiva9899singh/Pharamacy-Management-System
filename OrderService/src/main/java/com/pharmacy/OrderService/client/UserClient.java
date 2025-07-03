package com.pharmacy.OrderService.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.pharmacy.OrderService.Dto.UserResponse;

@FeignClient(name = "AUTH-SERVICE") // match the service name registered in Eureka
public interface UserClient {

    @GetMapping("/auth/user-by-username")

	UserResponse getUserByUsername(@RequestParam String username);
}


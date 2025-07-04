package com.pharmacy.OrderService.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "AUTH-SERVICE") // match the service name registered in Eureka
public interface UserClient {

	 @GetMapping("/auth/email/{username}")
	    String getEmailByUsername(@PathVariable("username") String username);
	 @GetMapping("/auth/extract-username")
	    String extractUsernameFromToken(@RequestHeader("Authorization") String token);

	}




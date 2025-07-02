package com.druginventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableWebMvc
public class DrugInventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrugInventoryServiceApplication.class, args);
	}

}

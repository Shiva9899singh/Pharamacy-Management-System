package com.pharmacy.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
	@Id
    private String id;
    private String doctorId;
    private String drugName;
    private int quantity;
    private String status; // PENDING, VERIFIED, PICKED_UP
    private String orderDate;
}




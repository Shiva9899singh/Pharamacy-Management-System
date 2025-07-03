package com.pharmacy.OrderService.Dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Data
@Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class UserResponse implements Serializable{
    private String username;
    private String email;
    private String role;
    private String contact;
}

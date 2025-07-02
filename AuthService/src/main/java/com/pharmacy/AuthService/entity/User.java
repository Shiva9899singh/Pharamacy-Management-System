package com.pharmacy.AuthService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull(message = "Username is required")
    @Size(min = 5,max = 15, message = "Username must be between 5 and 15 characters")
    private String username;

    @NotNull(message = "Email is required")
    @Email(message = "Please Enter a Valid Email")
    private String email;

    @NotNull(message = "Password is required")
    private String password;

    @NotNull(message = "Role is required")
    private String role;

    @NotNull(message = "Contact is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact must be a 10-digit number")
    private String contact;

}

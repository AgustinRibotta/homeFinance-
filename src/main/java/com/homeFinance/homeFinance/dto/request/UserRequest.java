package com.homeFinance.homeFinance.dto;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "The name is required")
        String name,
        
        @Email(message = "Email not valid")
        @NotBlank(message = "The email is required")
        String email,

        @NotBlank(message = "The password is required")
        String password,

        UUID householdId
        ) {
} 

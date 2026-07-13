package com.homeFinance.homeFinance.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

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

package com.homeFinance.homeFinance.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * RegisterRequest
 */
public record RegisterRequest(

    @NotBlank(message = "The user name is required") String userName,

    @Email(message = "Email not valid") @NotBlank(message = "The email is required") String email,

    @NotBlank(message = "The password is required") String password,

    @NotBlank(message = "The household name is required") String householdName) {
}

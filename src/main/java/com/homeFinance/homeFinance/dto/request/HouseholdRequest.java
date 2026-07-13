package com.homeFinance.homeFinance.dto.request;

import jakarta.validation.constraints.NotBlank;

public record HouseholdRequest(
        @NotBlank(message = "The name is required")
        String name
) {
}


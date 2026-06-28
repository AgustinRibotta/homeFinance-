package com.homeFinance.homeFinance.dto;

import java.util.UUID;



public record UserResponse(
        UUID id,
        String name,
        String email,
        HouseholdResponse household
        ) {}

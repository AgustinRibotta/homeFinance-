package com.homeFinance.homeFinance.dto;

import java.util.UUID;

import com.homeFinance.homeFinance.entity.Household;

public record UserResponse(
        UUID id,
        String name,
        String email,
        Household household
        ) {
}

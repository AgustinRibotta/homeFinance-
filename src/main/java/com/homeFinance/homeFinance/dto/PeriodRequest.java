package com.homeFinance.homeFinance.dto;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record PeriodRequest(

        @NotBlank(message = "Month is required")
        YearMonth month,

        BigDecimal initialAmount,
        
        @NotBlank(message = "Household is required")
        UUID householdId
        ) {}

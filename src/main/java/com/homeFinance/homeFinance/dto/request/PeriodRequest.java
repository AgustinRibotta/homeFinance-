package com.homeFinance.homeFinance.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.UUID;

public record PeriodRequest(

        @NotNull(message = "Month is required")
        YearMonth month,

        BigDecimal initialAmount,

        @NotNull(message = "Household is required")
        UUID householdId
) {
}

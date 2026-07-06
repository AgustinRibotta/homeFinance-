package com.homeFinance.homeFinance.dto;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.UUID;

public record PeriodResponse(
        UUID id,
        YearMonth month,
        BigDecimal initialAmount,
        BigDecimal closingAmount,
        BigDecimal totalMonthExpense,
        Boolean isClosed,
        HouseholdResponse household
) {
} 

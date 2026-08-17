package com.homeFinance.homeFinance.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record UserBalanceSummaryResponse(
    UUID id,

    BigDecimal totalIncome,

    BigDecimal totalExpense,

    BigDecimal balance,

    String name

) {
}

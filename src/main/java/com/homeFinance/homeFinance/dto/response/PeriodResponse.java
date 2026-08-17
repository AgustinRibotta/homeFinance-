package com.homeFinance.homeFinance.dto.response;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

public record PeriodResponse(
    UUID id,
    YearMonth month,
    BigDecimal initialAmount,
    BigDecimal closingAmount,
    BigDecimal totalMonthIncome,
    BigDecimal totalMonthExpense,
    Boolean isClosed,
    HouseholdResponse household,
    List<UserBalanceSummaryResponse> userBalances) {
}

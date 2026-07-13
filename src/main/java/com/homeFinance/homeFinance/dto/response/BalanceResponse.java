package com.homeFinance.homeFinance.dto.response;

import com.homeFinance.homeFinance.entity.Period;
import com.homeFinance.homeFinance.entity.User;

import java.math.BigDecimal;
import java.util.UUID;

public record BalanceResponse(
        UUID id,

        BigDecimal totalIncome,

        BigDecimal totalExpense,

        BigDecimal balance,

        User user,

        Period period

) {
}

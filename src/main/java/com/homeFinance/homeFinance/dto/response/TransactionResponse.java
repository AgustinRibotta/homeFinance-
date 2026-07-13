package com.homeFinance.homeFinance.dto.response;

import com.homeFinance.homeFinance.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransactionResponse(
        UUID id,
        BigDecimal amount,
        TransactionType type,
        String description,
        LocalDate date,
        UUID userBalanceId
) {
}

package com.homeFinance.homeFinance.dto.request;

import com.homeFinance.homeFinance.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionRequest(

        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be greater than 0")
        BigDecimal amount,

        @NotNull(message = "Transaction type is required")
        TransactionType type,

        @Size(max = 255, message = "Description cannot exceed 255 characters")
        String description,

        @NotNull(message = "userBalanceId is required")
        UUID userBalanceId

) {
}
package com.homeFinance.homeFinance.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record HouseholdSavingResponse(
        UUID id,
        UUID householdId,
        BigDecimal totalSaving
) {
}
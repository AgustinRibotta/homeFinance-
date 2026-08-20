package com.homeFinance.homeFinance.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record HouseholdSavingResponse(
    UUID id,
    BigDecimal totalSaving) {
}

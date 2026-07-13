package com.homeFinance.homeFinance.dto.response;

import java.util.UUID;

public record HouseholdResponse(
        UUID id,
        String name
) {
}


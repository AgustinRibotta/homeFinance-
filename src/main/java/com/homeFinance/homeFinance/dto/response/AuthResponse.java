package com.homeFinance.homeFinance.dto.response;

import java.util.UUID;

/**
 * AuthResponse
 */
public record AuthResponse(
    String toke,
    UUID userId,
    String name,
    UUID houseHoldId) {
}

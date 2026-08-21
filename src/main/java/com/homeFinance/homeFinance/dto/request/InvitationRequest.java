package com.homeFinance.homeFinance.dto.request;

import java.time.LocalDateTime;

/**
 * InvitationRequest
 */
public record InvitationRequest(
    LocalDateTime expiresAt,
    Integer maxUser) {
}

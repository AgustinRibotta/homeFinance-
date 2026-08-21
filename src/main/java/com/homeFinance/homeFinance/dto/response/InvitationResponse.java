package com.homeFinance.homeFinance.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * InvitationResponse
 */
public record InvitationResponse(
    UUID id,
    String code,
    UUID hauseholdId,
    LocalDateTime expiresAt,
    Integer maxUser,
    int currentUses,
    boolean active,
    UUID inviteById) {
}

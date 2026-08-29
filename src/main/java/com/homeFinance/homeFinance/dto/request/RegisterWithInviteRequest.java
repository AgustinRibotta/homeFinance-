package com.homeFinance.homeFinance.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * RegisterWithInviteRequest
 */
public record RegisterWithInviteRequest(
    @NotBlank String userName,
    @Email @NotBlank String email,
    @NotBlank String password,
    @NotBlank String inviteCode) {
}

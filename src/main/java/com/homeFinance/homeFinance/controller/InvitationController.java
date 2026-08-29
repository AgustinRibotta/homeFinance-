package com.homeFinance.homeFinance.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeFinance.homeFinance.dto.request.InvitationRequest;
import com.homeFinance.homeFinance.dto.response.InvitationResponse;
import com.homeFinance.homeFinance.service.InvitationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * InvitationController
 */
@Tag(name = "Invitation", description = "Managment of the Invitation")
@RestController
@RequestMapping("/invite")
public class InvitationController {

  private final InvitationService invitationService;

  public InvitationController(InvitationService invitationService) {
    this.invitationService = invitationService;
  }

  @Operation(summary = "Create Invitation by household id and user id")
  @PostMapping("{householdId}/{invitatedByUserId}/create")
  public ResponseEntity<InvitationResponse> post(@PathVariable UUID householdId, @PathVariable UUID invitatedByUserId,
      @RequestBody @Validated InvitationRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(invitationService.createInvitation(householdId, invitatedByUserId, request));
  }

  @Operation(summary = "Deactivate invitation code")
  @PatchMapping("{invitationId}/{userId}/deactivate")
  public ResponseEntity<Void> deactivate(@PathVariable UUID invitationId, @PathVariable UUID userId) {
    invitationService.deactivateInvitation(invitationId, userId);
    return ResponseEntity.ok().build();
  }

  @Operation(summary = "List active invitation")
  @GetMapping("{householdId")
  public ResponseEntity<List<InvitationResponse>> getActiveInvitation(@PathVariable UUID householdId,
      @PathVariable UUID userId) {
    return ResponseEntity.ok(invitationService.getActiveInvitations(householdId, userId));
  }
}

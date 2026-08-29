package com.homeFinance.homeFinance.service;

import java.util.List;
import java.util.UUID;

import com.homeFinance.homeFinance.dto.request.InvitationRequest;
import com.homeFinance.homeFinance.dto.response.InvitationResponse;

/**
 * InvitationService
 */
public interface InvitationService {

  InvitationResponse createInvitation(UUID householdId, UUID invitateByUserId, InvitationRequest request);

  void deactivateInvitation(UUID invitattionId, UUID requestingUserId);

  List<InvitationResponse> getActiveInvitations(UUID householdId, UUID requestingUserId);
}

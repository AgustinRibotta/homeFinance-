package com.homeFinance.homeFinance.service.imp;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homeFinance.homeFinance.dto.request.InvitationRequest;
import com.homeFinance.homeFinance.dto.response.InvitationResponse;
import com.homeFinance.homeFinance.entity.Household;
import com.homeFinance.homeFinance.entity.Invitation;
import com.homeFinance.homeFinance.entity.User;
import com.homeFinance.homeFinance.exeption.ForbiddenOperationException;
import com.homeFinance.homeFinance.exeption.ResourceNotFoundException;
import com.homeFinance.homeFinance.mapper.InvitationMapper;
import com.homeFinance.homeFinance.repository.HouseholdRepository;
import com.homeFinance.homeFinance.repository.InvitationRepository;
import com.homeFinance.homeFinance.repository.UserRepository;
import com.homeFinance.homeFinance.service.InvitationService;

/**
 * InvitationServiceImpl
 */
@Service
public class InvitationServiceImpl implements InvitationService {

  private final InvitationRepository invitationRepository;
  private final HouseholdRepository householdRepository;
  private final UserRepository userRepository;
  private final InvitationMapper invitationMapper;

  private static final int CODE_LENGTH = 8;
  private static final SecureRandom RANDOM = new SecureRandom();
  private static final String ALPHABET = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";

  public InvitationServiceImpl(InvitationRepository invitationRepository, HouseholdRepository householdRepository,
      UserRepository userRepository, InvitationMapper invitationMapper) {
    this.invitationRepository = invitationRepository;
    this.householdRepository = householdRepository;
    this.userRepository = userRepository;
    this.invitationMapper = invitationMapper;
  }

  @Override
  @Transactional
  public InvitationResponse createInvitation(UUID householdId, UUID invitedByUserId, InvitationRequest request) {
    Household household = householdRepository.findById(householdId)
        .orElseThrow(() -> new ResourceNotFoundException("House not found"));

    User invitedBy = userRepository.findById(invitedByUserId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    if (!invitedBy.getHousehold().getId().equals(householdId)) {
      throw new ForbiddenOperationException("You do not belong to this household");
    }

    Invitation invitation = new Invitation();
    invitation.setCode(generateUniqueCode());
    invitation.setHousehold(household);
    invitation.setInvitedBy(invitedBy);
    invitation.setExpiresAt(request.expiresAt());
    invitation.setMaxUsers(request.maxUsers());

    Invitation saved = invitationRepository.save(invitation);
    return invitationMapper.toResponse(saved);
  }

  @Override
  @Transactional
  public void deactivateInvitation(UUID invitationId, UUID requestingUserId) {
    Invitation invitation = invitationRepository.findById(invitationId)
        .orElseThrow(() -> new ResourceNotFoundException("Invitation not found"));

    User requester = userRepository.findById(requestingUserId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    if (!requester.getHousehold().getId().equals(invitation.getHousehold().getId())) {
      throw new ForbiddenOperationException("You do not belong to this household");
    }

    invitation.setActive(false);
    invitationRepository.save(invitation);
  }

  @Override
  @Transactional(readOnly = true)
  public List<InvitationResponse> getActiveInvitations(UUID householdId, UUID requestingUserId) {
    User requester = userRepository.findById(requestingUserId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    if (!requester.getHousehold().getId().equals(householdId)) {
      throw new ForbiddenOperationException("You do not belong to this household");
    }

    return invitationRepository.findByHouseholdIdAndActiveTrue(householdId)
        .stream()
        .map(invitationMapper::toResponse)
        .toList();
  }

  private String generateUniqueCode() {
    String code;
    do {
      code = generateRandomCode();
    } while (invitationRepository.findByCode(code).isPresent());
    return code;
  }

  private String generateRandomCode() {
    StringBuilder sb = new StringBuilder(CODE_LENGTH);
    for (int i = 0; i < CODE_LENGTH; i++) {
      sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
    }
    return sb.toString();
  }

}

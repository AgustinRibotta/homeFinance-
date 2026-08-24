package com.homeFinance.homeFinance.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeFinance.homeFinance.entity.Invitation;

/**
 * InvitationRepository
 */
public interface InvitationRepository extends JpaRepository<Invitation, UUID> {

  Optional<Invitation> findByCode(String code);

  List<Invitation> findByHouseholdIdAndActiveTrue(UUID householdId);
}

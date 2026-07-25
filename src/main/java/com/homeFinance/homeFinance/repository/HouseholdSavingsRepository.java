package com.homeFinance.homeFinance.repository;

import com.homeFinance.homeFinance.entity.HouseholdSavings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HouseholdSavingsRepository extends JpaRepository<HouseholdSavings, Long> {
  Optional<HouseholdSavings> findByHouseholdId(UUID id);
}

package com.homeFinance.homeFinance.repository;

import com.homeFinance.homeFinance.entity.HouseholdSaving;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HouseholdSavingRepository extends JpaRepository<HouseholdSaving, Long> {
    Optional<HouseholdSaving> findByHouseholdId(UUID id);
}

package com.homeFinance.homeFinance.repository;

import java.time.YearMonth;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeFinance.homeFinance.entity.Period;

import jakarta.validation.constraints.NotNull;

public interface PeriodRepository extends JpaRepository<Period, UUID> {

  boolean existsByHouseholdIdAndMonth(UUID id, @NotNull(message = "Month is required") YearMonth month);

  Optional<Period> findTopByHouseholdIdOrderByMonthDesc(UUID householdId);

}

package com.homeFinance.homeFinance.repository;

import com.homeFinance.homeFinance.entity.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserBalanceRepository extends JpaRepository<UserBalance, UUID> {
  List<UserBalance> findByPeriodId(UUID periodId);
}

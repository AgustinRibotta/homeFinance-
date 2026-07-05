package com.homeFinance.homeFinance.repository;

import com.homeFinance.homeFinance.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BalanceRepository extends JpaRepository<Balance, UUID> {
}

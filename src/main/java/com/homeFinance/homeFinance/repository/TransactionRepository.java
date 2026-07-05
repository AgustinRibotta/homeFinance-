package com.homeFinance.homeFinance.repository;

import com.homeFinance.homeFinance.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}

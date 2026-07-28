package com.homeFinance.homeFinance.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.homeFinance.homeFinance.entity.Transaction;
import com.homeFinance.homeFinance.enums.TransactionType;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

  @Query("""
          SELECT COALESCE(SUM(t.amount), 0)
          FROM Transaction t
          WHERE t.userBalance.id = :userBalanceId
            AND t.type = :type
      """)
  BigDecimal sumByUserBalanceIdAndType(
      @Param("userBalanceId") UUID userBalanceId,
      @Param("type") TransactionType type);

  List<Transaction> findByUserBalanceIdOrderByDateDesc(UUID userBalanceId);

}

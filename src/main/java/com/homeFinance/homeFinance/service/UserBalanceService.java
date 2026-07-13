package com.homeFinance.homeFinance.service;

import java.util.List;
import java.util.UUID;

import com.homeFinance.homeFinance.dto.response.UserBalanceResponse;
import com.homeFinance.homeFinance.entity.Transaction;

/**
 * UserBalanceService
 */
public interface UserBalanceService {

  UserBalanceResponse updateBalance(Transaction transacction);

  List<UserBalanceResponse> findBalanceByPeriodId(UUID periodId);

  List<UserBalanceResponse> findBalanceByUserid(UUID userId);
}

package com.homeFinance.homeFinance.service;

import java.util.List;
import java.util.UUID;

import com.homeFinance.homeFinance.dto.response.UserBalanceResponse;

/**
 * UserBalanceService
 */
public interface UserBalanceService {

  void updateBalance(UUID userBalanceId);

  List<UserBalanceResponse> findBalanceByPeriodId(UUID periodId);

  List<UserBalanceResponse> findBalanceByUserId(UUID userId);
}

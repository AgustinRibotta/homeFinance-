package com.homeFinance.homeFinance.service;

import java.util.List;
import java.util.UUID;

import com.homeFinance.homeFinance.dto.request.TransactionRequest;
import com.homeFinance.homeFinance.dto.response.TransactionResponse;

/**
 * TransactionService
 */
public interface TransactionService {

  TransactionResponse newTransaction(TransactionRequest transaction, UUID userBalanceId);

  List<TransactionResponse> findByUserBalanceId(UUID userBalanceId);
}

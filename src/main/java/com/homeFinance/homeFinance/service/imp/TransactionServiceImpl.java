package com.homeFinance.homeFinance.service.imp;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homeFinance.homeFinance.dto.request.TransactionRequest;
import com.homeFinance.homeFinance.dto.response.TransactionResponse;
import com.homeFinance.homeFinance.entity.Transaction;
import com.homeFinance.homeFinance.entity.UserBalance;
import com.homeFinance.homeFinance.exeption.InvalidPeriodStateException;
import com.homeFinance.homeFinance.mapper.TransactionMapper;
import com.homeFinance.homeFinance.repository.TransactionRepository;
import com.homeFinance.homeFinance.service.TransactionService;
import com.homeFinance.homeFinance.service.UserBalanceService;

/**
 * Transaction Service Implementation
 */
@Service
@Transactional(readOnly = true)
public class TransactionServiceImpl implements TransactionService {

  private final TransactionRepository transactionRepository;
  private final TransactionMapper transactionMapper;
  private final UserBalanceService userBalanceService;

  public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper,
      UserBalanceService userBalanceService) {
    this.transactionRepository = transactionRepository;
    this.transactionMapper = transactionMapper;
    this.userBalanceService = userBalanceService;
  }

  @Override
  public List<TransactionResponse> findByUserBalanceId(UUID userBalanceId) {
    userBalanceService.findById(userBalanceId);
    return transactionRepository.findByUserBalanceIdOrderByDateDesc(userBalanceId).stream()
        .map(transactionMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public TransactionResponse newTransaction(TransactionRequest req, UUID userBalaceId) {
    UserBalance balance = userBalanceService.findEntityById(userBalaceId);

    if (balance.getPeriod().getIsClosed()) {
      throw new InvalidPeriodStateException("Cannot generate new transaction of a close period");
    }

    Transaction transaction = transactionMapper.toEntity(req);
    transaction.setUserBalance(balance);

    transactionRepository.save(transaction);
    userBalanceService.updateBalance(userBalaceId);

    return transactionMapper.toResponse(transaction);
  }

}

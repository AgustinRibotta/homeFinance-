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
import com.homeFinance.homeFinance.exeption.ResourceNotFoundException;
import com.homeFinance.homeFinance.mapper.TransactionMapper;
import com.homeFinance.homeFinance.repository.TransactionRepository;
import com.homeFinance.homeFinance.repository.UserBalanceRepository;
import com.homeFinance.homeFinance.service.TransactionService;
import com.homeFinance.homeFinance.service.UserBalanceService;

/**
 * TransactionServiceImpl
 */
@Service
@Transactional(readOnly = true)
public class TransactionServiceImpl implements TransactionService {

  final private TransactionRepository transactionRepository;
  final private TransactionMapper transactionMapper;
  final private UserBalanceRepository userBalanceRepository;
  final private UserBalanceService userBalanceService;

  public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper,
      UserBalanceRepository userBalanceRepository, UserBalanceService userBalanceService) {
    this.transactionRepository = transactionRepository;
    this.transactionMapper = transactionMapper;
    this.userBalanceRepository = userBalanceRepository;
    this.userBalanceService = userBalanceService;
  }

  @Override
  public List<TransactionResponse> findByUserBalanceId(UUID userBalanceId) {
    userBalanceRepository.findById(userBalanceId)
        .orElseThrow(() -> new ResourceNotFoundException("User Balance not found"));

    return transactionRepository.findByUserBalanceIdOrderByDateDesc(userBalanceId).stream()
        .map(transactionMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public TransactionResponse newTransaction(TransactionRequest request) {
    UserBalance balance = userBalanceRepository.findById(request.userBalanceId())
        .orElseThrow(() -> new ResourceNotFoundException("User Balance Not found"));

    if (balance.getPeriod().getClosed()) {
      throw new InvalidPeriodStateException("Cannot generate new transaction of a close period");
    }

    Transaction transaction = transactionMapper.toEntity(request);
    transaction.setUserBalance(balance);

    transactionRepository.save(transaction);
    userBalanceService.updateBalance(request.userBalanceId());

    return transactionMapper.toResponse(transaction);
  }

}

package com.homeFinance.homeFinance.service.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homeFinance.homeFinance.dto.response.UserBalanceResponse;
import com.homeFinance.homeFinance.entity.UserBalance;
import com.homeFinance.homeFinance.enums.TransactionType;
import com.homeFinance.homeFinance.exeption.InvalidPeriodStateException;
import com.homeFinance.homeFinance.exeption.ResourceNotFoundException;
import com.homeFinance.homeFinance.mapper.UserBalanceMapper;
import com.homeFinance.homeFinance.repository.PeriodRepository;
import com.homeFinance.homeFinance.repository.TransactionRepository;
import com.homeFinance.homeFinance.repository.UserBalanceRepository;
import com.homeFinance.homeFinance.repository.UserRepository;
import com.homeFinance.homeFinance.service.UserBalanceService;

/**
 * UserBalanceServiceImpl
 */
@Service
@Transactional(readOnly = true)
public class UserBalanceServiceImpl implements UserBalanceService {

  private final UserBalanceRepository userBalanceRepository;
  private final PeriodRepository periodRepository;
  private final UserBalanceMapper userBalanceMapper;
  private final UserRepository userRepository;
  private final TransactionRepository transactionRepository;

  public UserBalanceServiceImpl(UserBalanceRepository userBalanceRepository, PeriodRepository periodRepository,
      UserBalanceMapper userBalanceMapper, UserRepository userRepository, TransactionRepository transactionRepository) {
    this.userBalanceRepository = userBalanceRepository;
    this.periodRepository = periodRepository;
    this.userBalanceMapper = userBalanceMapper;
    this.userRepository = userRepository;
    this.transactionRepository = transactionRepository;
  }

  @Override
  public List<UserBalanceResponse> findBalanceByPeriodId(UUID periodId) {
    periodRepository.findById(periodId)
        .orElseThrow(() -> new ResourceNotFoundException("Period not found"));
    return userBalanceRepository.findByPeriodId(periodId).stream()
        .map(userBalanceMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public List<UserBalanceResponse> findBalanceByUserId(UUID userId) {
    userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    return userBalanceRepository.findByUserId(userId).stream()
        .map(userBalanceMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public void updateBalance(UUID userBalanceId) {
    UserBalance balance = userBalanceRepository.findById(userBalanceId)
        .orElseThrow(() -> new ResourceNotFoundException("User Balance not found"));

    if (balance.getPeriod().getIsClosed()) {
      throw new InvalidPeriodStateException("Cannot update balance of a closed period");
    }

    BigDecimal totalIncome = Optional.ofNullable(
        transactionRepository.sumByUserBalanceIdAndType(userBalanceId, TransactionType.INCOME)).orElse(BigDecimal.ZERO);

    BigDecimal totalExpense = Optional.ofNullable(
        transactionRepository.sumByUserBalanceIdAndType(userBalanceId, TransactionType.EXPENSE))
        .orElse(BigDecimal.ZERO);

    balance.setTotalIncome(totalIncome);
    balance.setTotalExpense(totalExpense);
    balance.setBalance(totalIncome.subtract(totalExpense));
    userBalanceRepository.save(balance);
  }

}

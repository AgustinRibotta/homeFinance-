package com.homeFinance.homeFinance.service.imp;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homeFinance.homeFinance.dto.response.UserBalanceResponse;
import com.homeFinance.homeFinance.entity.Transaction;
import com.homeFinance.homeFinance.mapper.UserBalanceMapper;
import com.homeFinance.homeFinance.repository.PeriodRepository;
import com.homeFinance.homeFinance.repository.UserBalanceRepository;
import com.homeFinance.homeFinance.service.UserBalanceService;
import com.sun.nio.sctp.IllegalUnbindException;

/**
 * UserBalanceServiceImpl
 */
@Service
@Transactional(readOnly = true)
public class UserBalanceServiceImpl implements UserBalanceService {

  private final UserBalanceRepository userBalanceRepository;
  private final PeriodRepository periodRepository;
  private final UserBalanceMapper userBalanceMapper;

  public UserBalanceServiceImpl(UserBalanceRepository userBalanceRepository, PeriodRepository periodRepository,
      UserBalanceMapper userBalanceMapper) {
    this.userBalanceRepository = userBalanceRepository;
    this.periodRepository = periodRepository;
    this.userBalanceMapper = userBalanceMapper;
  }

  @Override
  public List<UserBalanceResponse> findBalanceByPeriodId(UUID periodId) {
    periodRepository.findById(periodId)
        .orElseThrow(() -> new IllegalUnbindException("Period not found"));
    return userBalanceRepository.findByPeriodId(periodId).stream()
        .map(balance -> {
          UserBalanceResponse dto = userBalanceMapper.toResponse(balance);
          return dto;
        })
        .collect(Collectors.toList());
  }

  @Override
  public List<UserBalanceResponse> findBalanceByUserid(UUID userId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  @Transactional
  public UserBalanceResponse updateBalance(Transaction transacction) {
    // TODO Auto-generated method stub
    return null;
  }

}

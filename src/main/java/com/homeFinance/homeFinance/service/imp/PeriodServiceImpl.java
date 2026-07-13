package com.homeFinance.homeFinance.service.imp;

import com.homeFinance.homeFinance.dto.request.PeriodRequest;
import com.homeFinance.homeFinance.dto.response.PeriodResponse;
import com.homeFinance.homeFinance.entity.*;
import com.homeFinance.homeFinance.mapper.PeriodMapper;
import com.homeFinance.homeFinance.repository.*;
import com.homeFinance.homeFinance.service.PeriodService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class PeriodServiceImpl implements PeriodService {

  private final PeriodRepository periodRepository;
  private final HouseholdRepository householdRepository;
  private final UserRepository userRepository;
  private final BalanceRepository balanceRepository;
  private final HouseholdSavingRepository householdSavingRepository;
  private final PeriodMapper periodMapper;

  public PeriodServiceImpl(PeriodRepository periodRepository, HouseholdRepository householdRepository,
      UserRepository userRepository, BalanceRepository balanceRepository,
      HouseholdSavingRepository householdSavingRepository, PeriodMapper periodMapper) {
    this.periodRepository = periodRepository;
    this.householdRepository = householdRepository;
    this.userRepository = userRepository;
    this.balanceRepository = balanceRepository;
    this.householdSavingRepository = householdSavingRepository;
    this.periodMapper = periodMapper;
  }

  @Override
  @Transactional
  public PeriodResponse createPeriod(PeriodRequest request) {
    Household household = householdRepository.findById(request.householdId())
        .orElseThrow(() -> new IllegalArgumentException("Household not found"));

    Period period = periodMapper.toEntity(request);
    period.setHousehold(household);
    period.setClosingAmount(BigDecimal.ZERO);
    period.setTotalMonthExpense(BigDecimal.ZERO);
    period.setClosed(false);

    Period saved = periodRepository.save(period);

    // Automatically create a UserBalance for each user in the household
    List<User> householdUsers = userRepository.findByHouseholdId(household.getId());

    List<UserBalance> balances = householdUsers.stream()
        .map(user -> {
          UserBalance userBalance = new UserBalance();
          userBalance.setUser(user);
          userBalance.setPeriod(saved);
          userBalance.setTotalIncome(BigDecimal.ZERO);
          userBalance.setTotalExpense(BigDecimal.ZERO);
          userBalance.setBalance(BigDecimal.ZERO);
          return userBalance;
        })
        .toList();

    balanceRepository.saveAll(balances);

    return periodMapper.toResponse(saved);
  }

  @Override
  public List<PeriodResponse> getAllPeriods() {
    return periodMapper.toResponseList(periodRepository.findAll());
  }

  @Override
  @Transactional
  public PeriodResponse closePeriod(UUID periodId) {
    // Validation
    Period period = periodRepository.findById(periodId)
        .orElseThrow(() -> new IllegalArgumentException("Period not found"));

    if (period.getClosed()) {
      throw new IllegalArgumentException("Period is already closed");
    }

    List<UserBalance> balances = balanceRepository.findByPeriodId(periodId);

    BigDecimal totalMonthExpense = balances.stream()
        .map(UserBalance::getTotalExpense)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    BigDecimal totalBalance = balances.stream()
        .map(UserBalance::getBalance)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    BigDecimal initial = period.getInitialAmount() != null
        ? period.getInitialAmount()
        : BigDecimal.ZERO;

    period.setTotalMonthExpense(totalMonthExpense);
    period.setClosingAmount(initial.add(totalBalance));
    period.setClosed(true);

    Period closed = periodRepository.save(period);

    // Once close, the period's total balance is added to the household's saving
    HouseholdSaving savings = householdSavingRepository
        .findByHouseholdId(period.getHousehold().getId())
        .orElseGet(() -> {
          HouseholdSaving newSavings = new HouseholdSaving();
          newSavings.setHousehold(period.getHousehold());
          newSavings.setTotalSaving(BigDecimal.ZERO);
          return newSavings;
        });

    savings.addPeriodBalance(totalBalance);
    householdSavingRepository.save(savings);

    return periodMapper.toResponse(closed);
  }
}

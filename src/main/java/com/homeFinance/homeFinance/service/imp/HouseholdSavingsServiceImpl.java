package com.homeFinance.homeFinance.service.imp;

import com.homeFinance.homeFinance.dto.response.HouseholdSavingResponse;
import com.homeFinance.homeFinance.entity.Household;
import com.homeFinance.homeFinance.entity.HouseholdSavings;
import com.homeFinance.homeFinance.exeption.ResourceNotFoundException;
import com.homeFinance.homeFinance.mapper.HouseholdSavingsMapper;
import com.homeFinance.homeFinance.repository.HouseholdSavingsRepository;
import com.homeFinance.homeFinance.service.HouseholdSavingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class HouseholdSavingsServiceImpl implements HouseholdSavingService {

  private final HouseholdSavingsRepository householdSavingRepository;
  private final HouseholdSavingsMapper householdSavingMapper;

  public HouseholdSavingsServiceImpl(HouseholdSavingsRepository householdSavingRepository,
      HouseholdSavingsMapper householdSavingMapper) {
    this.householdSavingRepository = householdSavingRepository;
    this.householdSavingMapper = householdSavingMapper;
  }

  @Override
  @Transactional(readOnly = true)
  public HouseholdSavingResponse getSavingsByHousehold(UUID householdId) {
    HouseholdSavings saving = householdSavingRepository.findByHouseholdId(householdId)
        .orElseThrow(() -> new ResourceNotFoundException("No savings found for this household"));

    return householdSavingMapper.toResponse(saving);
  }

  @Override
  public void create(Household household) {
    HouseholdSavings savings = new HouseholdSavings();
    savings.setHousehold(household);
    savings.setTotalSaving(BigDecimal.ZERO);
  }
}

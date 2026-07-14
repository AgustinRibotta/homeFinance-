package com.homeFinance.homeFinance.service.imp;

import com.homeFinance.homeFinance.dto.request.HouseholdRequest;
import com.homeFinance.homeFinance.dto.response.HouseholdResponse;
import com.homeFinance.homeFinance.entity.Household;
import com.homeFinance.homeFinance.entity.HouseholdSaving;
import com.homeFinance.homeFinance.exeption.ResourceNotFoundException;
import com.homeFinance.homeFinance.mapper.HouseholdMapper;
import com.homeFinance.homeFinance.repository.HouseholdRepository;
import com.homeFinance.homeFinance.repository.HouseholdSavingRepository;
import com.homeFinance.homeFinance.service.HouseholdService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class HouseholdServiceImpl implements HouseholdService {

  private final HouseholdRepository householdRepository;
  private final HouseholdSavingRepository householdSavingRepository;
  private final HouseholdMapper householdMapper;

  public HouseholdServiceImpl(HouseholdRepository householdRepository,
      HouseholdSavingRepository householdSavingRepository,
      HouseholdMapper householdMapper) {
    this.householdRepository = householdRepository;
    this.householdSavingRepository = householdSavingRepository;
    this.householdMapper = householdMapper;
  }

  @Override
  @Transactional
  public HouseholdResponse create(HouseholdRequest request) {
    Household entity = householdMapper.toEntity(request);
    Household saved = householdRepository.save(entity);

    HouseholdSaving savings = new HouseholdSaving();
    savings.setHousehold(saved);
    savings.setTotalSaving(BigDecimal.ZERO);
    householdSavingRepository.save(savings);

    return householdMapper.toResponse(saved);
  }

  @Override
  public HouseholdResponse findById(UUID id) {
    Household entity = findHouseholdOrThrow(id);
    return householdMapper.toResponse(entity);
  }

  @Override
  @Transactional
  public HouseholdResponse update(UUID id, HouseholdRequest request) {
    Household entity = findHouseholdOrThrow(id);
    entity.setName(request.name());
    Household saved = householdRepository.save(entity);
    return householdMapper.toResponse(saved);
  }

  // Helpers
  private Household findHouseholdOrThrow(UUID id) {
    return householdRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Household not found. Id: " + id));
  }
}

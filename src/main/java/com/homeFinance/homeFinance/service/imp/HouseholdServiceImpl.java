package com.homeFinance.homeFinance.service.imp;

import com.homeFinance.homeFinance.dto.request.HouseholdRequest;
import com.homeFinance.homeFinance.dto.response.HouseholdResponse;
import com.homeFinance.homeFinance.entity.Household;
import com.homeFinance.homeFinance.exeption.ResourceNotFoundException;
import com.homeFinance.homeFinance.mapper.HouseholdMapper;
import com.homeFinance.homeFinance.repository.HouseholdRepository;
import com.homeFinance.homeFinance.service.HouseholdSavingService;
import com.homeFinance.homeFinance.service.HouseholdService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class HouseholdServiceImpl implements HouseholdService {

  private final HouseholdRepository householdRepository;
  private final HouseholdMapper householdMapper;
  private final HouseholdSavingService householdSavingService;

  public HouseholdServiceImpl(HouseholdRepository householdRepository,
      HouseholdMapper householdMapper,
      HouseholdSavingService householdSavingService) {
    this.householdRepository = householdRepository;
    this.householdMapper = householdMapper;
    this.householdSavingService = householdSavingService;
  }

  @Override
  @Transactional
  public HouseholdResponse create(HouseholdRequest req) {
    Household entity = householdMapper.toEntity(req);
    Household saved = householdRepository.save(entity);
    householdSavingService.create(saved);
    return householdMapper.toResponse(saved);
  }

  @Override
  @Transactional
  public HouseholdResponse update(UUID id, HouseholdRequest req) {
    Household entity = findHouseholdOrThrow(id);
    entity.setName(req.name());
    Household saved = householdRepository.save(entity);
    return householdMapper.toResponse(saved);
  }

  // Helpers
  private Household findHouseholdOrThrow(UUID id) {
    return householdRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Household not found. Id: " + id));
  }

  @Override
  public Household findEntityById(UUID id) {
    return findHouseholdOrThrow(id);
  }

  @Override
  public HouseholdResponse findById(UUID id) {
    Household entity = findHouseholdOrThrow(id);
    return householdMapper.toResponse(entity);
  }
}

package com.homeFinance.homeFinance.service.imp;

import com.homeFinance.homeFinance.dto.HouseholdRequest;
import com.homeFinance.homeFinance.dto.HouseholdResponse;
import com.homeFinance.homeFinance.entity.Household;
import com.homeFinance.homeFinance.mapper.HouseholdMapper;
import com.homeFinance.homeFinance.repository.HouseholdRepository;
import com.homeFinance.homeFinance.service.HouseholdService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@Transactional(readOnly = true)
public class HouseholdServiceImpl implements HouseholdService {

    private final HouseholdRepository householdRepository;
    private final HouseholdMapper householdMapper;

    public HouseholdServiceImpl(HouseholdRepository householdRepository, HouseholdMapper householdMapper) {
        this.householdRepository = householdRepository;
        this.householdMapper = householdMapper;
    }

    @Override
    @Transactional
    public HouseholdResponse create(HouseholdRequest request) {
        Household entity = householdMapper.toEntity(request);
        Household saved = householdRepository.save(entity);
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
                .orElseThrow(() -> new EntityNotFoundException("Id: " + id));
    }

}

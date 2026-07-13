package com.homeFinance.homeFinance.service.imp;

import com.homeFinance.homeFinance.dto.response.HouseholdSavingResponse;
import com.homeFinance.homeFinance.entity.HouseholdSaving;
import com.homeFinance.homeFinance.mapper.HouseholdSavingMapper;
import com.homeFinance.homeFinance.repository.HouseholdSavingRepository;
import com.homeFinance.homeFinance.service.HouseholdSavingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class HouseholdSavingServiceImpl implements HouseholdSavingService {

    private final HouseholdSavingRepository householdSavingRepository;
    private final HouseholdSavingMapper householdSavingMapper;

    public HouseholdSavingServiceImpl(HouseholdSavingRepository householdSavingRepository,
                                      HouseholdSavingMapper householdSavingMapper) {
        this.householdSavingRepository = householdSavingRepository;
        this.householdSavingMapper = householdSavingMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public HouseholdSavingResponse getSavingsByHousehold(UUID householdId) {
        HouseholdSaving saving = householdSavingRepository.findByHouseholdId(householdId)
                .orElseThrow(() -> new IllegalArgumentException("No savings found for this household"));

        return householdSavingMapper.toResponse(saving);
    }
}
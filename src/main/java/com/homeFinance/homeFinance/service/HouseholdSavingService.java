package com.homeFinance.homeFinance.service;

import com.homeFinance.homeFinance.dto.response.HouseholdSavingResponse;

import java.util.UUID;

public interface HouseholdSavingService {

    HouseholdSavingResponse getSavingsByHousehold(UUID householdId);
}

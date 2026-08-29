package com.homeFinance.homeFinance.service;

import com.homeFinance.homeFinance.dto.response.HouseholdSavingResponse;
import com.homeFinance.homeFinance.entity.Household;

import java.util.UUID;

public interface HouseholdSavingService {
  void create(Household household);

  HouseholdSavingResponse getSavingsByHousehold(UUID householdId);
}

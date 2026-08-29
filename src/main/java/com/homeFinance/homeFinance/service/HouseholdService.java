package com.homeFinance.homeFinance.service;

import com.homeFinance.homeFinance.dto.request.HouseholdRequest;
import com.homeFinance.homeFinance.dto.response.HouseholdResponse;
import com.homeFinance.homeFinance.entity.Household;

import java.util.UUID;

public interface HouseholdService {
  HouseholdResponse create(HouseholdRequest request);

  HouseholdResponse findById(UUID id);

  Household findEntityById(UUID id);

  HouseholdResponse update(UUID id, HouseholdRequest request);

}

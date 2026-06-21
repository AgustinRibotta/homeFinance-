package com.homeFinance.homeFinance.service;

import java.util.UUID;

import com.homeFinance.homeFinance.dto.HouseholdRequest;
import com.homeFinance.homeFinance.dto.HouseholdResponse;

public interface HouseholdService {
    HouseholdResponse create(HouseholdRequest request);
    HouseholdResponse findById(UUID id);
    HouseholdResponse update(UUID id, HouseholdRequest request);
    void addMember(UUID HouseholdId, UUID userId);
}

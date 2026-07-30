package com.homeFinance.homeFinance.controller;

import com.homeFinance.homeFinance.dto.response.HouseholdSavingResponse;
import com.homeFinance.homeFinance.service.HouseholdSavingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/households")
public class HouseholdSavingController {

  private final HouseholdSavingService householdSavingService;

  public HouseholdSavingController(HouseholdSavingService householdSavingService) {
    this.householdSavingService = householdSavingService;
  }

  @GetMapping("/{householdId}/savings")
  public ResponseEntity<HouseholdSavingResponse> getSavings(@PathVariable UUID householdId) {
    HouseholdSavingResponse response = householdSavingService.getSavingsByHousehold(householdId);
    return ResponseEntity.ok(response);
  }
}

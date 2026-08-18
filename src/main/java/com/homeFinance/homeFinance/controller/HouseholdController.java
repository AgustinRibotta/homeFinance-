package com.homeFinance.homeFinance.controller;

import com.homeFinance.homeFinance.dto.request.HouseholdRequest;
import com.homeFinance.homeFinance.dto.response.HouseholdResponse;
import com.homeFinance.homeFinance.service.HouseholdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Households", description = "Managment of the familiar home")
@RestController
@RequestMapping("/households")
public class HouseholdController {

  private final HouseholdService householdService;

  public HouseholdController(HouseholdService householdService) {
    this.householdService = householdService;
  }

  @Operation(summary = "Find home by id")
  @GetMapping("/{id}")
  public ResponseEntity<HouseholdResponse> getById(@PathVariable UUID id) {
    return ResponseEntity.ok(householdService.findById(id));
  }

  @Operation(summary = "Update home")
  @PutMapping("/{id}")
  public ResponseEntity<HouseholdResponse> update(@PathVariable UUID id,
      @Validated @RequestBody HouseholdRequest request) {
    return ResponseEntity.ok(householdService.update(id, request));
  }
}

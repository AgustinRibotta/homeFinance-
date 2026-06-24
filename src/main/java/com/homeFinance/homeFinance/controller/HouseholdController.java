package com.homeFinance.homeFinance.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeFinance.homeFinance.dto.HouseholdRequest;
import com.homeFinance.homeFinance.dto.HouseholdResponse;
import com.homeFinance.homeFinance.service.HouseholdService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

// Test LazyGit
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
   
    @Operation(summary = "Create new home")
    @PostMapping()
    public ResponseEntity<HouseholdResponse> post(@Validated @RequestBody HouseholdRequest request) {
        HouseholdResponse response = householdService.create(request);
        URI location = URI.create("/api/v1/households/" + response.id());
        return ResponseEntity.created(location).body(response);
    }

    @Operation(summary = "Update home")
    @PostMapping("/{id}")
    public ResponseEntity<HouseholdResponse> update(@PathVariable UUID id, @Validated @RequestBody HouseholdRequest request) { 
        return ResponseEntity.ok(householdService.update(id, request));
    }
}

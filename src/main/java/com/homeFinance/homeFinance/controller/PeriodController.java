package com.homeFinance.homeFinance.controller;

import com.homeFinance.homeFinance.dto.PeriodRequest;
import com.homeFinance.homeFinance.dto.PeriodResponse;
import com.homeFinance.homeFinance.service.PeriodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/periods")
@Tag(name = "Periods", description = "Household monthly periods management")
public class PeriodController {

    private final PeriodService periodService;

    public PeriodController(PeriodService periodService) {
        this.periodService = periodService;
    }

    @PostMapping
    @Operation(summary = "Create a new period", description = "Creates a new period and initializes UserBalance")
    public ResponseEntity<PeriodResponse> create(@Valid @RequestBody PeriodRequest request) {
        PeriodResponse response = periodService.createPeriod(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Get all periods", description = "Returns a list of all periods")
    public ResponseEntity<List<PeriodResponse>> getAll() {
        List<PeriodResponse> response = periodService.getAllPeriods();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/close")
    @Operation(summary = "Close a period", description = "Closes a period and consolidates household savings")
    public ResponseEntity<PeriodResponse> close(@PathVariable UUID id) {
        return ResponseEntity.ok(periodService.closePeriod(id));
    }
}

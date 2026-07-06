package com.homeFinance.homeFinance.service;

import com.homeFinance.homeFinance.dto.PeriodRequest;
import com.homeFinance.homeFinance.dto.PeriodResponse;

import java.util.List;
import java.util.UUID;

public interface PeriodService {

    PeriodResponse createPeriod(PeriodRequest request);

    List<PeriodResponse> getAllPeriods();

    PeriodResponse closePeriod(UUID id);
}

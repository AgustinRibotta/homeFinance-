package com.homeFinance.homeFinance.service;

import com.homeFinance.homeFinance.dto.request.PeriodRequest;
import com.homeFinance.homeFinance.dto.response.PeriodResponse;

import java.util.List;
import java.util.UUID;

public interface PeriodService {

    PeriodResponse createPeriod(PeriodRequest request);

    List<PeriodResponse> getAllPeriods();

    PeriodResponse closePeriod(UUID id);
}

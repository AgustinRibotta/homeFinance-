package com.homeFinance.homeFinance.mapper;

import com.homeFinance.homeFinance.dto.request.PeriodRequest;
import com.homeFinance.homeFinance.dto.response.PeriodResponse;
import com.homeFinance.homeFinance.entity.Period;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PeriodMapper {

  // household is not mapped here because MapStruct only has a UUID,
  // not the actual Household entity. It must be fetched and set
  // manually in the Service layer.
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "household", ignore = true)
  @Mapping(target = "closingAmount", ignore = true)
  @Mapping(target = "totalMonthIncome", ignore = true)
  @Mapping(target = "totalMonthExpense", ignore = true)
  @Mapping(target = "isClosed", ignore = true)
  @Mapping(target = "userBalances", ignore = true)
  @Mapping(target = "version", ignore = true)
  Period toEntity(PeriodRequest request);

  PeriodResponse toResponse(Period period);

  List<PeriodResponse> toResponseList(List<Period> periods);
}

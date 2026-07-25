package com.homeFinance.homeFinance.mapper;

import com.homeFinance.homeFinance.dto.response.HouseholdSavingResponse;
import com.homeFinance.homeFinance.entity.HouseholdSavings;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HouseholdSavingsMapper {
  HouseholdSavingResponse toResponse(HouseholdSavings householdSaving);
}

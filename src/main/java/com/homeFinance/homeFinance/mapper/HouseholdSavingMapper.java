package com.homeFinance.homeFinance.mapper;

import com.homeFinance.homeFinance.dto.response.HouseholdSavingResponse;
import com.homeFinance.homeFinance.entity.HouseholdSaving;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HouseholdSavingMapper {
    HouseholdSavingResponse toResponse(HouseholdSaving householdSaving);
}

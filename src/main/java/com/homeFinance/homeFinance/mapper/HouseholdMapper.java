package com.homeFinance.homeFinance.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.homeFinance.homeFinance.dto.HouseholdRequest;
import com.homeFinance.homeFinance.dto.HouseholdResponse;
import com.homeFinance.homeFinance.entity.Household;

@Mapper(componentModel = "spring")
public interface HouseholdMapper {
    @Mapping(target = "id", ignore = true)
    Household toEntity (HouseholdRequest request);
    HouseholdResponse toResponse(Household household);
}

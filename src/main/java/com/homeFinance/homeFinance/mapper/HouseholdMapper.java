package com.homeFinance.homeFinance.mapper;

import org.mapstruct.Mapper;

import com.homeFinance.homeFinance.dto.HouseholdRequest;
import com.homeFinance.homeFinance.dto.HouseholdResponse;
import com.homeFinance.homeFinance.entity.Household;

@Mapper(componentModel = "spring")
public interface HouseholdMapper {
    Household toEntity (HouseholdRequest request);
    HouseholdResponse toResponse(Household household);
}

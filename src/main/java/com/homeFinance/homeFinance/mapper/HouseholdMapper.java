package com.homeFinance.homeFinance.mapper;

import com.homeFinance.homeFinance.dto.HouseholdRequest;
import com.homeFinance.homeFinance.dto.HouseholdResponse;
import com.homeFinance.homeFinance.entity.Household;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HouseholdMapper {

    // users is not mapped here because a new Household starts empty
    // (no users yet). Users are added later through their own
    // creation flow, in the Service layer.
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "users", ignore = true)
    Household toEntity(HouseholdRequest request);

    HouseholdResponse toResponse(Household entity);
}
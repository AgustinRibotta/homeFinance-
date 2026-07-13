package com.homeFinance.homeFinance.mapper;

import com.homeFinance.homeFinance.dto.request.UserRequest;
import com.homeFinance.homeFinance.dto.response.UserResponse;
import com.homeFinance.homeFinance.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // household is not mapped here because MapStruct only has a UUID
    // (householdId) in the request, not the actual Household entity.
    // It must be fetched by ID and set manually in the Service layer.
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "household", ignore = true)
    User toEntity(UserRequest request);

    UserResponse toResponse(User entity);
}
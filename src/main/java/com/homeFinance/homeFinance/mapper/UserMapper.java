package com.homeFinance.homeFinance.mapper;

import com.homeFinance.homeFinance.dto.UserRequest;
import com.homeFinance.homeFinance.dto.UserResponse;
import com.homeFinance.homeFinance.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper { 
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "household", ignore = true)
    User toEntity (UserRequest request);
    UserResponse toResponse (User entity);
}

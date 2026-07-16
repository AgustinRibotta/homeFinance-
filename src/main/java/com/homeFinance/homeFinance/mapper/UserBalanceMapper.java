package com.homeFinance.homeFinance.mapper;

import com.homeFinance.homeFinance.dto.response.UserBalanceResponse;
import com.homeFinance.homeFinance.entity.UserBalance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserBalanceMapper {
  UserBalanceResponse toResponse(UserBalance entity);
}

package com.homeFinance.homeFinance.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.homeFinance.homeFinance.dto.response.UserBalanceSummaryResponse;
import com.homeFinance.homeFinance.entity.UserBalance;

@Mapper(componentModel = "spring")
public interface UserBalanceSummaryMapper {

  @Mapping(target = "name", source = "user.name")
  UserBalanceSummaryResponse toSummary(UserBalance userBalance);
}

package com.homeFinance.homeFinance.mapper;

import com.homeFinance.homeFinance.dto.response.BalanceResponse;
import com.homeFinance.homeFinance.entity.UserBalance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BalanceMapper {
    BalanceResponse toResponse(UserBalance entity);
}

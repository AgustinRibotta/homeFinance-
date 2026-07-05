package com.homeFinance.homeFinance.mapper;

import com.homeFinance.homeFinance.dto.BalanceResponse;
import com.homeFinance.homeFinance.entity.Balance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BalanceMapper {
    BalanceResponse toResponse(Balance entity);
}

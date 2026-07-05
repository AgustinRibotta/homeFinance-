package com.homeFinance.homeFinance.mapper;

import com.homeFinance.homeFinance.dto.TransactionRequest;
import com.homeFinance.homeFinance.dto.TransactionResponse;
import com.homeFinance.homeFinance.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    // userBalance is not mapped here because MapStruct only has a UUID,
    // not the actual UserBalance entity. It must be fetched and set
    // manually in the Service layer.
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "userBalance", ignore = true)
    Transaction toEntity(TransactionRequest request);

    @Mapping(target = "userBalanceId", source = "userBalance.id")
    TransactionResponse toResponse(Transaction transaction);
}
package com.homeFinance.homeFinance.mapper;

import org.mapstruct.Mapper;

import com.homeFinance.homeFinance.dto.response.InvitationResponse;
import com.homeFinance.homeFinance.entity.Invitation;

/**
 * InvitationMapper
 */
@Mapper(componentModel = "spring")
public interface InvitationMapper {

  InvitationResponse toResponse(Invitation entity);

}

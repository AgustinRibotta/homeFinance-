package com.homeFinance.homeFinance.service.imp;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.homeFinance.homeFinance.dto.HouseholdRequest;
import com.homeFinance.homeFinance.dto.HouseholdResponse;
import com.homeFinance.homeFinance.repository.HouseholdRepository;
import com.homeFinance.homeFinance.service.HouseholdService;

@Service
public class HouseholdServiceImp implements HouseholdService {

    private final HouseholdRepository householdRepository;


	public HouseholdServiceImp(HouseholdRepository householdRepository) {
		this.householdRepository = householdRepository;
	}

	@Override
	public void addMember(UUID HouseholdId, UUID userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HouseholdResponse create(HouseholdRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HouseholdResponse findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HouseholdResponse update(UUID id, HouseholdRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

    
}

package com.homeFinance.homeFinance.service.imp;

import jakarta.persistence.EntityNotFoundException;

import com.homeFinance.homeFinance.dto.UserRequest;
import com.homeFinance.homeFinance.dto.UserResponse;
import com.homeFinance.homeFinance.repository.HouseholdRepository;
import com.homeFinance.homeFinance.repository.UserRepository;
import com.homeFinance.homeFinance.service.UserService;

public class UserServiceImp implements UserService{

    private final UserRepository userRepository;
    private final HouseholdRepository householdRepository;

    public UserServiceImp(UserRepository userRepository, HouseholdRepository householdRepository){
        this.userRepository = userRepository;
		this.householdRepository = householdRepository;
    }

	@Override
	public UserResponse create(UserRequest user) {
        householdRepository.findById(user.householdId())
            .orElseThrow(() -> new EntityNotFoundException("Housegold not found"));
    }

}

package com.homeFinance.homeFinance.service.imp;

import com.homeFinance.homeFinance.dto.UserRequest;
import com.homeFinance.homeFinance.dto.UserResponse;
import com.homeFinance.homeFinance.entity.Household;
import com.homeFinance.homeFinance.entity.User;
import com.homeFinance.homeFinance.mapper.UserMapper;
import com.homeFinance.homeFinance.repository.HouseholdRepository;
import com.homeFinance.homeFinance.repository.UserRepository;
import com.homeFinance.homeFinance.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final HouseholdRepository householdRepository;
    private final UserMapper userMapper;

    public UserServiceImp(UserRepository userRepository, HouseholdRepository householdRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.householdRepository = householdRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse create(UserRequest request) {
        Household household = householdRepository.findById(request.householdId()).orElseThrow(() -> new EntityNotFoundException("Household not found"));

        User user = userMapper.toEntity(request);
        user.setHousehold(household);

        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponse findById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

        return userMapper.toResponse(user);
    }

}

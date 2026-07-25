package com.homeFinance.homeFinance.service.imp;

import com.homeFinance.homeFinance.dto.request.UserRequest;
import com.homeFinance.homeFinance.dto.response.UserResponse;
import com.homeFinance.homeFinance.entity.Household;
import com.homeFinance.homeFinance.entity.User;
import com.homeFinance.homeFinance.exeption.DuplicateResourceException;
import com.homeFinance.homeFinance.exeption.ResourceNotFoundException;
import com.homeFinance.homeFinance.mapper.UserMapper;
import com.homeFinance.homeFinance.repository.HouseholdRepository;
import com.homeFinance.homeFinance.repository.UserRepository;
import com.homeFinance.homeFinance.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final HouseholdRepository householdRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  public UserServiceImpl(UserRepository userRepository, HouseholdRepository householdRepository,
      UserMapper userMapper, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.householdRepository = householdRepository;
    this.userMapper = userMapper;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  @Transactional
  public UserResponse create(UserRequest request) {
    Household household = householdRepository.findById(request.householdId())
        .orElseThrow(() -> new ResourceNotFoundException("Household not found"));

    if (userRepository.existsByEmail(request.email())) {
      throw new DuplicateResourceException("Email already registered");
    }
    User user = userMapper.toEntity(request);
    user.setPassword(passwordEncoder.encode(request.password()));
    user.setHousehold(household);

    return userMapper.toResponse(userRepository.save(user));
  }

  @Override
  public UserResponse findById(UUID id) {
    User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

    return userMapper.toResponse(user);
  }

}

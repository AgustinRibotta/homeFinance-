package com.homeFinance.homeFinance.service.imp;

import com.homeFinance.homeFinance.dto.request.UserRequest;
import com.homeFinance.homeFinance.dto.response.UserResponse;
import com.homeFinance.homeFinance.entity.Household;
import com.homeFinance.homeFinance.entity.User;
import com.homeFinance.homeFinance.exeption.DuplicateResourceException;
import com.homeFinance.homeFinance.exeption.ResourceNotFoundException;
import com.homeFinance.homeFinance.mapper.UserMapper;
import com.homeFinance.homeFinance.repository.UserRepository;
import com.homeFinance.homeFinance.service.HouseholdService;
import com.homeFinance.homeFinance.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;
  private final HouseholdService householdService;

  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,
      PasswordEncoder passwordEncoder, HouseholdService householdService) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.passwordEncoder = passwordEncoder;
    this.householdService = householdService;
  }

  @Override
  @Transactional
  public UserResponse create(UserRequest req) {
    Household household = householdService.findEntityById(req.householdId());

    if (userRepository.existsByEmail(req.email())) {
      throw new DuplicateResourceException("Email already registered");
    }

    User user = userMapper.toEntity(req);
    user.setPassword(passwordEncoder.encode(req.password()));
    user.setHousehold(household);

    return userMapper.toResponse(userRepository.save(user));
  }

  @Override
  public UserResponse findById(UUID id) {
    User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    return userMapper.toResponse(user);
  }

}

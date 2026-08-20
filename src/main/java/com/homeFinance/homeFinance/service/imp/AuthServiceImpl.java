package com.homeFinance.homeFinance.service.imp;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homeFinance.homeFinance.dto.request.HouseholdRequest;
import com.homeFinance.homeFinance.dto.request.LoginRequest;
import com.homeFinance.homeFinance.dto.request.RegisterRequest;
import com.homeFinance.homeFinance.dto.request.UserRequest;
import com.homeFinance.homeFinance.dto.response.AuthResponse;
import com.homeFinance.homeFinance.dto.response.HouseholdResponse;
import com.homeFinance.homeFinance.dto.response.UserResponse;
import com.homeFinance.homeFinance.entity.User;
import com.homeFinance.homeFinance.mapper.UserMapper;
import com.homeFinance.homeFinance.repository.UserRepository;
import com.homeFinance.homeFinance.service.AuthService;
import com.homeFinance.homeFinance.service.HouseholdService;
import com.homeFinance.homeFinance.service.JwtService;
import com.homeFinance.homeFinance.service.UserService;

/**
 * AuthServiceImpl
 */
@Service
public class AuthServiceImpl implements AuthService {

  private final UserService userService;
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final HouseholdService householdService;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;

  public AuthServiceImpl(UserService userService, HouseholdService householdService, JwtService jwtService,
      UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
    this.householdService = householdService;
    this.userService = userService;
    this.jwtService = jwtService;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.userMapper = userMapper;
  }

  @Override
  public AuthResponse login(LoginRequest request) {
    User user = userRepository.findByEmail(request.email())
        .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

    if (!passwordEncoder.matches(request.password(), user.getPassword())) {
      throw new BadCredentialsException("Invalid credentials");
    }

    UserResponse userResponse = userMapper.toResponse(user);
    String token = jwtService.generateToken(userResponse);

    return new AuthResponse(token, user.getId(), user.getName(), user.getHousehold().getId());
  }

  @Override
  @Transactional
  public AuthResponse register(RegisterRequest request) {
    HouseholdResponse houseHold = householdService.create(new HouseholdRequest(request.householdName()));
    UserResponse user = userService.create(
        new UserRequest(request.userName(), request.email(), request.password(), houseHold.id()));
    String token = jwtService.generateToken(user);
    return new AuthResponse(token, user.id(), user.name(), user.household().id());
  }

}

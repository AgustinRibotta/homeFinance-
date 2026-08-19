package com.homeFinance.homeFinance.service.imp;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homeFinance.homeFinance.dto.request.HouseholdRequest;
import com.homeFinance.homeFinance.dto.request.LoginRequest;
import com.homeFinance.homeFinance.dto.request.RegisterRequest;
import com.homeFinance.homeFinance.dto.request.UserRequest;
import com.homeFinance.homeFinance.dto.response.AuthResponse;
import com.homeFinance.homeFinance.dto.response.HouseholdResponse;
import com.homeFinance.homeFinance.dto.response.UserResponse;
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
  private final HouseholdService householdService;
  private final JwtService jwtService;

  public AuthServiceImpl(UserService userService, HouseholdService householdService, JwtService jwtService) {
    this.householdService = householdService;
    this.userService = userService;
    this.jwtService = jwtService;
  }

  @Override
  public AuthResponse login(LoginRequest request) {
    return null;
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

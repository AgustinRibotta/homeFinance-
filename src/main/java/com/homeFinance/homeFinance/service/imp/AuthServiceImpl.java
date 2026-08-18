package com.homeFinance.homeFinance.service.imp;

import com.homeFinance.homeFinance.dto.request.LoginRequest;
import com.homeFinance.homeFinance.dto.request.RegisterRequest;
import com.homeFinance.homeFinance.dto.response.UserResponse;
import com.homeFinance.homeFinance.service.AuthService;
import com.homeFinance.homeFinance.service.HouseholdService;
import com.homeFinance.homeFinance.service.UserService;

/**
 * AuthServiceImpl
 */
public class AuthServiceImpl implements AuthService {

  private final UserService userService;
  private final HouseholdService householdService;

  public AuthServiceImpl(UserService userService, HouseholdService householdService) {
    this.householdService = householdService;
    this.userService = userService;
  }

  @Override
  public UserResponse login(LoginRequest request) {
    return null;
  }

  @Override
  public UserResponse register(RegisterRequest request) {
    return null;
  }

}

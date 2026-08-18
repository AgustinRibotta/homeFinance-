package com.homeFinance.homeFinance.service;

import com.homeFinance.homeFinance.dto.request.LoginRequest;
import com.homeFinance.homeFinance.dto.request.RegisterRequest;
import com.homeFinance.homeFinance.dto.response.UserResponse;

/**
 * AuthService
 */
public interface AuthService {

  UserResponse login(LoginRequest request);

  UserResponse register(RegisterRequest request);
}

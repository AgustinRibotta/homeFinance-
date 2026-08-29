package com.homeFinance.homeFinance.service;

import com.homeFinance.homeFinance.dto.request.LoginRequest;
import com.homeFinance.homeFinance.dto.request.RegisterRequest;
import com.homeFinance.homeFinance.dto.request.RegisterWithInviteRequest;
import com.homeFinance.homeFinance.dto.response.AuthResponse;

/**
 * AuthService
 */
public interface AuthService {

  AuthResponse login(LoginRequest request);

  AuthResponse register(RegisterRequest request);

  AuthResponse registerWithInvitation(RegisterWithInviteRequest request);
}

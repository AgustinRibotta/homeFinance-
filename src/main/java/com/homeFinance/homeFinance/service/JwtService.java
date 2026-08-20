package com.homeFinance.homeFinance.service;

import com.homeFinance.homeFinance.dto.response.UserResponse;

/**
 * JwtService
 */

public interface JwtService {

  String generateToken(UserResponse user);
}

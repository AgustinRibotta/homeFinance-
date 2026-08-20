package com.homeFinance.homeFinance.service;

import com.homeFinance.homeFinance.dto.request.UserRequest;
import com.homeFinance.homeFinance.dto.response.UserResponse;

import java.util.UUID;

public interface UserService {

  UserResponse findById(UUID id);

  UserResponse create(UserRequest user);
}

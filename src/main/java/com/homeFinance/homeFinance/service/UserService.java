package com.homeFinance.homeFinance.service;

import java.util.UUID;

import com.homeFinance.homeFinance.dto.UserRequest;
import com.homeFinance.homeFinance.dto.UserResponse;

public interface UserService {

    UserResponse findById (UUID id);
    UserResponse create (UserRequest user);
}

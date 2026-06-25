package com.homeFinance.homeFinance.service;

import com.homeFinance.homeFinance.dto.UserRequest;
import com.homeFinance.homeFinance.dto.UserResponse;

public interface UserService {

    UserResponse create (UserRequest user);
}

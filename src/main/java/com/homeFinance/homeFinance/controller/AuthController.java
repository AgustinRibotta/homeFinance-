package com.homeFinance.homeFinance.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeFinance.homeFinance.dto.request.HouseholdRequest;
import com.homeFinance.homeFinance.dto.request.UserRequest;
import com.homeFinance.homeFinance.dto.response.UserResponse;
import com.homeFinance.homeFinance.service.HouseholdService;
import com.homeFinance.homeFinance.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * AuthController
 */
@Tag(name = "Authenticaate")
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final UserService userService;
  private final HouseholdService householdService;

  @PostMapping("/register")
  public ResponseEntity<UserResponse> post(@Validated @RequestBody UserRequest userRequestm,
      @Validated @RequestBody HouseholdRequest householdRequest) {

  }
}

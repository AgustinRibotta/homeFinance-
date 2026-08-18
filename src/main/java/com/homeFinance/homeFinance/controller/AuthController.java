package com.homeFinance.homeFinance.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeFinance.homeFinance.dto.request.LoginRequest;
import com.homeFinance.homeFinance.dto.request.RegisterRequest;
import com.homeFinance.homeFinance.dto.response.UserResponse;
import com.homeFinance.homeFinance.service.AuthService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * AuthController
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/register")
  public ResponseEntity<UserResponse> register(@Validated @RequestBody RegisterRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
  }

  @PostMapping("/login")
  public ResponseEntity<UserResponse> login(@Validated @RequestBody LoginRequest request) {
    return ResponseEntity.ok(authService.login(request));
  }
}

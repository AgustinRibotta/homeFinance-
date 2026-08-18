package com.homeFinance.homeFinance.controller;

import com.homeFinance.homeFinance.dto.response.UserResponse;
import com.homeFinance.homeFinance.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "User", description = "Managmen of the user")
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Operation(summary = "Find user by id")
  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> getById(@PathVariable UUID id) {
    return ResponseEntity.ok(userService.findById(id));
  }
}

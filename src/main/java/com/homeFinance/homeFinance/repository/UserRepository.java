package com.homeFinance.homeFinance.repository;

import com.homeFinance.homeFinance.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

  List<User> findByHouseholdId(UUID id);

  Optional<User> findByEmail(String email);

  boolean existsByEmail(
      @Email(message = "Email not valid") @NotBlank(message = "The email is required") String email);

}

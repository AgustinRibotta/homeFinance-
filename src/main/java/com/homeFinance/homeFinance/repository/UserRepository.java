package com.homeFinance.homeFinance.repository;

import com.homeFinance.homeFinance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findByHouseholdId(UUID id);
}
    


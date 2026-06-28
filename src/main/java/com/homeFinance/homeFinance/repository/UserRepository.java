package com.homeFinance.homeFinance.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeFinance.homeFinance.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> { 
    
}
    


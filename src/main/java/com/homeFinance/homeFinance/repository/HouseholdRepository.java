package com.homeFinance.homeFinance.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeFinance.homeFinance.entity.Household;

public interface HouseholdRepository extends JpaRepository<Household, UUID> {

    
}

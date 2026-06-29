package com.homeFinance.homeFinance.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeFinance.homeFinance.entity.Period;

public interface PeriodRepository extends JpaRepository<Period, UUID>{

    
}

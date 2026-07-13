package com.homeFinance.homeFinance.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "household_saving")
public class HouseholdSaving {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "total_saving", nullable = false, precision = 19, scale = 2)
    private BigDecimal totalSaving = BigDecimal.ZERO;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_id", nullable = false, unique = true)
    private Household household;

    public HouseholdSaving() {
    }

    public HouseholdSaving(UUID id, BigDecimal totalSaving, Household household) {
        this.id = id;
        this.totalSaving = totalSaving;
        this.household = household;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getTotalSaving() {
        return totalSaving;
    }

    public void setTotalSaving(BigDecimal totalSaving) {
        this.totalSaving = totalSaving;
    }

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }

    // Domain logic based on the calculation rules:
    // Savings = SUM of all closed periods
    public void addPeriodBalance(BigDecimal periodBalance) {
        this.totalSaving = this.totalSaving.add(periodBalance);
    }
}


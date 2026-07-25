package com.homeFinance.homeFinance.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "household_saving")
public class HouseholdSavings {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "total_saving", nullable = false, precision = 19, scale = 2)
  private BigDecimal totalSaving = BigDecimal.ZERO;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "household_id", nullable = false, unique = true)
  private Household household;

  @Version
  @Column(name = "version", nullable = false)
  private Long version;

  public HouseholdSavings() {
  }

  public HouseholdSavings(UUID id, BigDecimal totalSaving, Household household) {
    this.id = id;
    this.totalSaving = totalSaving != null ? totalSaving : BigDecimal.ZERO;
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

  public Long getVersion() {
    return version;
  }

  // Domain logic based on the calculation rules:
  // Savings = SUM of all closed periods
  public void addPeriodBalance(BigDecimal periodBalance) {
    if (periodBalance == null) {
      throw new IllegalArgumentException("periodBalance cannot be null");
    }
    this.totalSaving = this.totalSaving.add(periodBalance);
  }
}

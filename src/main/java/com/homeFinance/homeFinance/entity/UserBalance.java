package com.homeFinance.homeFinance.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Balance
 */
@Table(name = "user_balance", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "period_id" }))
@Entity
public class UserBalance {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false, precision = 15, scale = 2)
  private BigDecimal totalIncome = BigDecimal.ZERO;

  @Column(nullable = false, precision = 15, scale = 2)
  private BigDecimal totalExpense = BigDecimal.ZERO;

  @Column(nullable = false, precision = 15, scale = 2)
  private BigDecimal balance = BigDecimal.ZERO;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "period_id", nullable = false)
  private Period period;

  @Version
  @Column(name = "version", nullable = false)
  private Long version;

  public UserBalance(UUID id, BigDecimal totalIncome, BigDecimal totalExpense, BigDecimal balance, User user,
      Period period) {
    this.id = id;
    this.totalIncome = totalIncome;
    this.totalExpense = totalExpense;
    this.balance = balance;
    this.user = user;
    this.period = period;
  }

  public UserBalance() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public BigDecimal getTotalIncome() {
    return totalIncome;
  }

  public void setTotalIncome(BigDecimal totalIncome) {
    this.totalIncome = totalIncome;
  }

  public BigDecimal getTotalExpense() {
    return totalExpense;
  }

  public void setTotalExpense(BigDecimal totalExpense) {
    this.totalExpense = totalExpense;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Period getPeriod() {
    return period;
  }

  public void setPeriod(Period period) {
    this.period = period;
  }

  public Long getVersion() {
    return version;
  }

}

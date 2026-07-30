package com.homeFinance.homeFinance.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Period
 */
@Table(name = "periods", uniqueConstraints = @UniqueConstraint(columnNames = { "household_id", "month" }))
@Entity
public class Period {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false, length = 7)
  private YearMonth month;

  @Column(precision = 15, scale = 2)
  private BigDecimal initialAmount = BigDecimal.ZERO;

  @Column(precision = 15, scale = 2)
  private BigDecimal closingAmount = BigDecimal.ZERO;

  @Column(precision = 15, scale = 2)
  private BigDecimal totalMonthIncome = BigDecimal.ZERO;

  @Column(precision = 15, scale = 2)
  private BigDecimal totalMonthExpense = BigDecimal.ZERO;

  @Column(nullable = false)
  private Boolean isClosed = false;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "household_id", nullable = false)
  private Household household;

  @OneToMany(mappedBy = "period", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<UserBalance> userBalances = new ArrayList<>();

  @Version
  @Column(name = "version", nullable = false)
  private Long version;

  public Period(UUID id, YearMonth month, BigDecimal initialAmount, BigDecimal closingAmount,
      BigDecimal totalMonthIncome, BigDecimal totalMonthExpense, Boolean isClosed, Household household,
      List<UserBalance> userBalances, Long version) {
    this.id = id;
    this.month = month;
    this.initialAmount = initialAmount;
    this.closingAmount = closingAmount;
    this.totalMonthIncome = totalMonthIncome;
    this.totalMonthExpense = totalMonthExpense;
    this.isClosed = isClosed;
    this.household = household;
    this.userBalances = userBalances;
    this.version = version;
  }

  public Period() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public YearMonth getMonth() {
    return month;
  }

  public void setMonth(YearMonth month) {
    this.month = month;
  }

  public BigDecimal getInitialAmount() {
    return initialAmount;
  }

  public void setInitialAmount(BigDecimal initialAmount) {
    this.initialAmount = initialAmount;
  }

  public BigDecimal getClosingAmount() {
    return closingAmount;
  }

  public void setClosingAmount(BigDecimal closingAmount) {
    this.closingAmount = closingAmount;
  }

  public BigDecimal getTotalMonthIncome() {
    return totalMonthIncome;
  }

  public void setTotalMonthIncome(BigDecimal totalMonthIncome) {
    this.totalMonthIncome = totalMonthIncome;
  }

  public BigDecimal getTotalMonthExpense() {
    return totalMonthExpense;
  }

  public void setTotalMonthExpense(BigDecimal totalMonthExpense) {
    this.totalMonthExpense = totalMonthExpense;
  }

  public Boolean getIsClosed() {
    return isClosed;
  }

  public void setIsClosed(Boolean isClosed) {
    this.isClosed = isClosed;
  }

  public Household getHousehold() {
    return household;
  }

  public void setHousehold(Household household) {
    this.household = household;
  }

  public List<UserBalance> getUserBalances() {
    return userBalances;
  }

  public void setUserBalances(List<UserBalance> userBalances) {
    this.userBalances = userBalances;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

}

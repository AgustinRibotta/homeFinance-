package com.homeFinance.homeFinance.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.UUID;

/**
 * Period
 */
@Table(name = "periods")
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
    private BigDecimal totalMonthExpense = BigDecimal.ZERO;

    @Column(nullable = false)
    private Boolean isClosed = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_id", nullable = false)
    private Household household;

    public Period(UUID id) {
        this.id = id;
    }

    public Period(UUID id, YearMonth month, BigDecimal initialAmount, BigDecimal closingAmount, BigDecimal totalMonthExpense, Boolean isClosed, Household household) {
        this.id = id;
        this.month = month;
        this.initialAmount = initialAmount;
        this.closingAmount = closingAmount;
        this.totalMonthExpense = totalMonthExpense;
        this.isClosed = isClosed;
        this.household = household;
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

    public BigDecimal getTotalMonthExpense() {
        return totalMonthExpense;
    }

    public void setTotalMonthExpense(BigDecimal totalMonthExpense) {
        this.totalMonthExpense = totalMonthExpense;
    }

    public Boolean getClosed() {
        return isClosed;
    }

    public void setClosed(Boolean closed) {
        isClosed = closed;
    }

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }
}

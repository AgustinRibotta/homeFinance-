package com.homeFinance.homeFinance.entity;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.UUID;

import jakarta.persistence.*;

@Table(name = "periods")
@Entity
public class Period {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 7)
    private YearMonth month;

    @Column(precision = 15, scale = 2)
    private BigDecimal initialAmount;

    @Column(precision = 15, scale = 2)
    private BigDecimal closingAmount;

    @Column(precision = 15, scale = 2)
    private BigDecimal totalMonthExpense;

    @Column(nullable = false)
    private Boolean isClosed = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_id", nullable = false)
    private Household household;
}

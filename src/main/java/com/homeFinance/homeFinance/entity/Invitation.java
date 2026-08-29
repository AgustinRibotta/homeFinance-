package com.homeFinance.homeFinance.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;

/**
 * Invitation
 *
 */
@Entity
@Table(name = "invitations")
public class Invitation {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id")
  private UUID id;

  @Column(name = "code", unique = true, nullable = false, length = 20)
  private String code;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "household_id", nullable = false)
  private Household household;

  @Column(name = "expires_at")
  private LocalDateTime expiresAt;

  @Column(name = "max_uses")
  private Integer maxUsers;

  @Column(name = "current_users", nullable = false)
  private int currentUsers = 0;

  @Column(name = "active", nullable = false)
  private boolean active = true;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "invited_by", nullable = false)
  private User invitedBy;

  public Invitation() {
  }

  public Invitation(UUID id, String code, Household household, LocalDateTime expiresAt, Integer maxUsers,
      int currentUsers, boolean active, User invitedBy) {
    this.id = id;
    this.code = code;
    this.household = household;
    this.expiresAt = expiresAt;
    this.maxUsers = maxUsers;
    this.currentUsers = currentUsers;
    this.active = active;
    this.invitedBy = invitedBy;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Household getHousehold() {
    return household;
  }

  public void setHousehold(Household household) {
    this.household = household;
  }

  public LocalDateTime getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(LocalDateTime expiresAt) {
    this.expiresAt = expiresAt;
  }

  public Integer getMaxUsers() {
    return maxUsers;
  }

  public void setMaxUsers(Integer maxUses) {
    this.maxUsers = maxUses;
  }

  public int getCurrentUsers() {
    return currentUsers;
  }

  public void setCurrentUsers(int currentUsers) {
    this.currentUsers = currentUsers;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public User getInvitedBy() {
    return invitedBy;
  }

  public void setInvitedBy(User invitedBy) {
    this.invitedBy = invitedBy;
  }

}

package com.homeFinance.homeFinance.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "household")
public class Household {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(length = 100, nullable = false)
  private String name;

  @OneToMany(mappedBy = "household", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<User> users = new ArrayList<>();

  @Version
  @Column(name = "version", nullable = false)
  private Long version;

  public Household() {
  }

  public Household(UUID id, String name, List<User> users) {
    this.id = id;
    this.name = name;
    this.users = users != null ? new ArrayList<>(users) : new ArrayList<>();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public Long getVersion() {
    return version;
  }
}

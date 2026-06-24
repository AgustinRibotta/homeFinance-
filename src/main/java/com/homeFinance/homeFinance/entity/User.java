package com.homeFinance.homeFinance.entity;

import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_profile")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(length = 250, nullable = false)
    private String name;

    @Column(length = 250, nullable = false, unique = true)
    private String email;

    @Column(length = 250, nullable = false)
    private String password;

	@OneToOne( fetch = FetchType.LAZY , cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "household_id", referencedColumnName = "id")
    private Household household;

	public User() {
	}

	public User(UUID id, String name, String email, String password, Household household) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.household = household;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Household getHousehold() {
		return household;
	}

	public void setHousehold(Household household) {
		this.household = household;
	}
    
}

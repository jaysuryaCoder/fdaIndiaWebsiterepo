package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Fdatable")
public class FDAIndiaCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@Column(nullable = false)
	private String name;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(unique = true, nullable = false)
	private String username;
	@Column(nullable = false)
	private String mobile;
	@Column(nullable = false)
	private String state;
	@Column(nullable = false)
	private String password;
	private Long token;
	@Column(nullable = true)
	private Double totalAmount;
	@Column(nullable = true)
	private Double deposit;
	@Column(nullable = true)
	private Double withdrawalAmount;
	@Column(nullable = true)
	private Double transaction;
	@Column(nullable = true)
	private Double borrowMoney;

}

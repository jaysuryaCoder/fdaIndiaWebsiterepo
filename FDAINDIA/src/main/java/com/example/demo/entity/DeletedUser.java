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

@Entity
@Table(name="deleteuser")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeletedUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@Column(unique = true, nullable = false)
	private String name;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(unique = true, nullable = false)
	private String username;
	@Column(unique = true, nullable = false)
	private String mobile;
	@Column(unique = true, nullable = false)
	private String state;
	@Column(unique = true, nullable = false)
	private String password;
	private Long token;
}

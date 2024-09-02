package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.KendraKyc;

@Repository
public interface KendraKycRepository extends JpaRepository<KendraKyc, Long> {
	public KendraKyc findByUserId(Long id);
	public Optional<KendraKyc> getUserFileByUserId(Long id);
	
	public KendraKyc findByEmail(String email);
}


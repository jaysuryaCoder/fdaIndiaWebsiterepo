package com.example.demo.repository;


import com.example.demo.entity.CardKycEntity;
import com.example.demo.entity.FDAIndiaCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardKycRepository extends JpaRepository<CardKycEntity, Long> {

	FDAIndiaCard  findByEmail(String email);
	
}
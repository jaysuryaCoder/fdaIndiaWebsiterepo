package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.FdaEmployee;

@Repository
public interface FdaEmployeeRepository extends JpaRepository<FdaEmployee, Long> {
	public Optional<FdaEmployee> findById(Long id);
}

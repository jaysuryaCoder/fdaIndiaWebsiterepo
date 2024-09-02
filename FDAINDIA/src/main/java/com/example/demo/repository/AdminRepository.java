package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	@Query("SELECT a FROM Admin a WHERE (a.username = :username OR a.email = :email) AND a.password = :password")
	Admin findByUsernameOrEmailAndPassword(@Param("username") String username, @Param("email") String email,
			@Param("password") String password);
	
	Admin findByUsername(String username);
}

package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.UserDataDTO;
import com.example.demo.entity.FDAIndiaCard;

@Repository
public interface CardRepsository extends JpaRepository<FDAIndiaCard, Long> {
	public FDAIndiaCard findByUsername(String name);
	public FDAIndiaCard findByEmail(String email);
	public FDAIndiaCard findByUserId(Long id);
	
	 @Query("SELECT u FROM FDAIndiaCard u WHERE (u.username = :username OR u.email = :email) AND u.password = :password")
	 public FDAIndiaCard findByUserNameOrEmailAndPassword(@Param("username") String username, @Param("email") String email, @Param("password") String password);
	
	 @Query("SELECT new com.example.demo.dto.UserDataDTO(u.userId, u.name, u.email, u.username, u.mobile, u.state, u.token, u.totalAmount, u.deposit, u.withdrawalAmount, u.transaction, u.borrowMoney) FROM FDAIndiaCard u")
	 List<UserDataDTO> findAllUsersWithoutPassword();
	 
	 public FDAIndiaCard findByToken(Long token);
}

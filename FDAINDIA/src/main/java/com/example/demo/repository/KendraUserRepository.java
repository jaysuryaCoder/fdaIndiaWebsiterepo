package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.UserDataDTO;
import com.example.demo.dto.UserKendraDataDto;
import com.example.demo.entity.FDAIndiaCard;
import com.example.demo.entity.KendraUser;

public interface KendraUserRepository extends JpaRepository<KendraUser, Long> {
	
	public KendraUser findByUsername(String name);

	public KendraUser findByEmail(String email);

	@Query("SELECT new com.example.demo.dto.UserKendraDataDto(u.userId, u.name, u.email, u.username, u.mobile, u.state, u.token, u.total_amount) FROM KendraUser u WHERE u.email = :email")
	UserKendraDataDto findByEmailWithoutPassword(@Param("email") String email);


	
	public KendraUser findByUserId(Long id);

	public KendraUser findByToken(Long token);
	
	@Query("SELECT u FROM KendraUser u WHERE (u.username = :username OR u.email = :email) AND u.password = :password")
	public KendraUser findByUserNameOrEmailAndPassword(@Param("username") String username,
			@Param("email") String email, @Param("password") String password);
	
	
	   @Query("SELECT new com.example.demo.dto.UserKendraDataDto(u.userId, u.name, u.email, u.username, u.mobile, u.state, u.token,u.total_amount) FROM KendraUser u")
	    List<UserDataDTO> findAllUsersWithoutPassword();
	

	@Query("SELECT u FROM KendraUser u WHERE u.username = :username AND u.email = :email")
	public FDAIndiaCard findByUserNameAndEmail(@Param("username") String username, @Param("email") String email);
}
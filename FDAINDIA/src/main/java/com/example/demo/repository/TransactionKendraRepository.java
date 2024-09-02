package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.KendraUser;
import com.example.demo.entity.TransactionForKendra;

public interface TransactionKendraRepository extends JpaRepository<TransactionForKendra, Long>{
	 long countByUser(KendraUser user);
	 
	    @Query("SELECT t FROM TransactionForKendra t WHERE t.user.userId = :userId ORDER BY t.tid DESC")
	    TransactionForKendra findTopByUserIdOrderByTidDesc(Long userId);
	    
	    List<TransactionForKendra> getAllByUserUserId(Long userId);
	    
	    List<TransactionForKendra> findByUserUserId(Long userId);
	    
}

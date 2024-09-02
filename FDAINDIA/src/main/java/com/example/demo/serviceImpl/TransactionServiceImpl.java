package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TransactionDto;
import com.example.demo.entity.KendraUser;
import com.example.demo.entity.TransactionForKendra;
import com.example.demo.repository.KendraUserRepository;
import com.example.demo.repository.TransactionKendraRepository;
import com.example.demo.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private TransactionKendraRepository transactionRepository;

	@Autowired
	private KendraUserRepository kendraRepository;

	@Override
	public TransactionForKendra createTransaction(TransactionForKendra transaction) {
		Optional<KendraUser> userOptional = kendraRepository.findById(transaction.getUser().getUserId());
		if (userOptional.isPresent()) {
			transaction.setUser(userOptional.get());
			return transactionRepository.save(transaction);
		}
		throw new RuntimeException("User not found");
	}

	@Override
	public long countTransactionsByUser(Long userId) {
		Optional<KendraUser> userOptional = kendraRepository.findById(userId);
		if (userOptional.isPresent()) {
			System.out.println(userOptional.get().getName() + " Has total number of Tranasction till now is  "
					+ transactionRepository.countByUser(userOptional.get()));
			return transactionRepository.countByUser(userOptional.get());
		}
		throw new RuntimeException("User not found");
	}

	@Override
	public List<TransactionForKendra> getAllTransactionsByUserId(Long userId) {
		return transactionRepository.getAllByUserUserId(userId);
	}

	@Override
	public List<TransactionDto> getTransactionsByUserId(Long userId) {
        return transactionRepository.findByUserUserId(userId).stream().map(transaction -> modelMapper.map(transaction, TransactionDto.class)).collect(Collectors.toList());
    }
}

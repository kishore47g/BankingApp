package com.SpringBank.Service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.SpringBank.Entity.Account;
import com.SpringBank.Mapper.AccountMapper;
import com.SpringBank.Repo.AccountRepo;
import com.SpringBank.Service.AccountService;
import com.SpringBank.dto.AccountDto;

@Service
public class AccountServiceImpl implements AccountService{
	private AccountRepo accountRepo;
	
	AccountServiceImpl(AccountRepo accountRepo)
	{
		this.accountRepo=accountRepo;
	}

	@Override
	public AccountDto createAccount(AccountDto accountdto) {
		Account account = AccountMapper.mapToAccount(accountdto);
		System.out.println(account.getId()+"xxxx"+account.getAccoundHolderName()+"xxxxx"+account.getBalance());
		Account savedAccount=accountRepo.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(long id) {
		Account account=accountRepo
				.findById(id)
				.orElseThrow(()->new RuntimeException("Account Does not Exist"));
		return AccountMapper.mapToAccountDto(account);
	}

	public AccountDto deposit(long id, double amount) {
		
		Account account=accountRepo
				.findById(id)
				.orElseThrow(()->new RuntimeException("Account Does not Exist"));
		double total=account.getBalance()+amount;
		account.setBalance(total);
		Account savedAccount=accountRepo.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}
	
	public AccountDto withdraw(long id, double amount) {
		
		Account account=accountRepo
				.findById(id)
				.orElseThrow(()->new RuntimeException("Account Does not Exist"));
		if(amount > account.getBalance())throw new RuntimeException("Insufficient Balance");
		double total=account.getBalance()-amount;
		account.setBalance(total);
		Account savedAccount=accountRepo.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepo.findAll();
		return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
		 
	}

	@Override
	public void deleteAccount(long id) {
		Account account=accountRepo
				.findById(id)
				.orElseThrow(()->new RuntimeException("Account Does not Exist"));
		accountRepo.deleteById(id);
		
	}



}

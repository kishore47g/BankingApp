package com.SpringBank.Service;

import java.util.List;

import com.SpringBank.dto.AccountDto;

public interface AccountService {

	public AccountDto createAccount(AccountDto accountdto);
	public AccountDto getAccountById(long id);
	public AccountDto deposit(long id,double amount);
	public AccountDto withdraw(long id,double amount);
	public List<AccountDto> getAllAccounts();
	public void deleteAccount(long id);
	
}

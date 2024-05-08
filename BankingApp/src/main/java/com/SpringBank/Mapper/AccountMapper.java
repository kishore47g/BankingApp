package com.SpringBank.Mapper;

import com.SpringBank.Entity.Account;
import com.SpringBank.dto.AccountDto;

public class AccountMapper {
	
	public static Account mapToAccount(AccountDto accountDto)
	{
		Account account= new Account(accountDto.getId(),accountDto.getAccoundHolderName(),accountDto.getBalance());
		return account;
	}
	
	public static AccountDto mapToAccountDto( Account account)
	{
		AccountDto accountDto= new AccountDto(account.getId(),account.getAccoundHolderName(),account.getBalance());
		return accountDto;
	}

}

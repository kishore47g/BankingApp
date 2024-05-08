package com.SpringBank.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBank.Service.AccountService;
import com.SpringBank.dto.AccountDto;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	
	private AccountService accountservice;
	
	AccountController(AccountService accountservice){
		this.accountservice=accountservice;
		
	}
	
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountdto)
	{
		System.out.println(accountdto.getBalance()+" "+accountdto.getAccoundHolderName());
		return new ResponseEntity<>(accountservice.createAccount(accountdto),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable long id){
		AccountDto accountdto=accountservice.getAccountById(id);
		return ResponseEntity.ok(accountdto);
	}

	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable long id,@RequestBody Map<String,Double> request)
	{
		double amount= request.get("amount");
		AccountDto accountDto = accountservice.deposit(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable long id,@RequestBody Map<String,Double> request)
	{
		double amount= request.get("amount");
		AccountDto accountDto = accountservice.withdraw(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	//Get all Accounts Rest API
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		List<AccountDto> accounts=accountservice.getAllAccounts();
		return ResponseEntity.ok(accounts);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable long id){
		accountservice.deleteAccount(id);
		return ResponseEntity.ok("Account Deleted Successsfully");
	}
	
}

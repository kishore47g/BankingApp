package com.SpringBank.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBank.Entity.Account;

public interface AccountRepo extends JpaRepository<Account, Long>{
	
}

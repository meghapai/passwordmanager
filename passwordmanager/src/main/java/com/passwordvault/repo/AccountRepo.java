package com.passwordvault.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.passwordvault.entity.AccountPassword;

public interface AccountRepo extends JpaRepository<AccountPassword,Long> {

	public AccountPassword findByUserEmail(String userEmail);

	public List<AccountPassword> findAllPasswordsByUserEmail(String email);
	
	
}

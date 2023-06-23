package com.passwordvault.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.passwordvault.config.JwtAuthenticationFilter;
import com.passwordvault.entity.AccountPassword;
import com.passwordvault.repo.AccountRepo;

@Service
public class AccountPasswordService {
	
		
	@Autowired
	private AccountRepo accountRepo;
	
	
	public AccountPassword addPassword(AccountPassword accountPasswords) {
		String currentUser = JwtAuthenticationFilter.CURRENT_USER;
	  	//User user = userRepo.findByEmail(currentUser);
	  	AccountPassword localPassword;
	  	if(currentUser != null) {
	  	accountPasswords.setUserEmail(currentUser);
	  	localPassword = this.accountRepo.save(accountPasswords);
	  	}
	  	else {
	  		throw new UsernameNotFoundException("Please login to your account");
	  	}
		return localPassword;	
	}
	
//	public AccountPassword updatePassword(String email, AccountPassword accountPassword) {
//		String currentUser = JwtAuthenticationFilter.CURRENT_USER;
//		AccountPassword account = accountRepo.findByUserEmail(email);
//		if(currentUser!=null) {
//		account.setAccountname(accountPassword.getAccountname());
//		account.setUsername(accountPassword.getUsername());
//		account.setAccPassword(accountPassword.getAccPassword());
//		account.setUserEmail(email);
//		}
//		else {
//			throw new UsernameNotFoundException("This user does not exist");
//		}
//		return this.accountRepo.save(account);
//		}
	
	public List<AccountPassword> findAllPasswords(){
		return accountRepo.findAll();
	}
	
	public List<AccountPassword> findAllPasswordsByEmail(String email) {
		return (List<AccountPassword>) accountRepo.findAllPasswordsByUserEmail(email);
	}
	
	public void deletePassword(Long passwordId) {
		accountRepo.deleteById(passwordId);
	}
	
	public AccountPassword resetPassword(Long passId, AccountPassword accountPassword) {
		AccountPassword localPassword = accountRepo.findById(passId).get();
		localPassword.setAccountname(accountPassword.getAccountname());
		localPassword.setAccPassword(accountPassword.getAccPassword());
		localPassword.setUsername(accountPassword.getUsername());
		return accountRepo.save(localPassword);
	}

	public AccountPassword findPasswordById(Long id) {
		
		return accountRepo.findById(id).get();
	}
	

}

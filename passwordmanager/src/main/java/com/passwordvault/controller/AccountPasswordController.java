package com.passwordvault.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.passwordvault.entity.AccountPassword;
import com.passwordvault.service.AccountPasswordService;

@RestController
@CrossOrigin
@RequestMapping("/accountPassword")
public class AccountPasswordController {
	
	@Autowired
	private AccountPasswordService accountPasswordService;
	
	@PostMapping("/add-password")
	public ResponseEntity<AccountPassword> addPassword(@RequestBody AccountPassword password) {
		AccountPassword accountPassword = accountPasswordService.addPassword(password);
		return new ResponseEntity<>(accountPassword,HttpStatus.CREATED);
	}
	
//	@PutMapping("/update-password/{email}")
//	public ResponseEntity<AccountPassword> updatePassword(@PathVariable("email") String email, @RequestBody AccountPassword password){
//		AccountPassword updatedPassword = accountPasswordService.updatePassword(email,password);
//		return new ResponseEntity<>(updatedPassword,HttpStatus.OK);
//	}
	
	@GetMapping("/all")
	public ResponseEntity<List<AccountPassword>> getAllPasswords(){
		List<AccountPassword> allPasswords = accountPasswordService.findAllPasswords();
		return new ResponseEntity<>(allPasswords,HttpStatus.OK);
	}
	
	@GetMapping("/find-password/{email}")
	public ResponseEntity<List<AccountPassword>> getPasswordByEmail(@PathVariable("email") String email){
		List<AccountPassword> passwords = accountPasswordService.findAllPasswordsByEmail(email);
		return new ResponseEntity<>(passwords,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePassword(@PathVariable("id") Long id){
		accountPasswordService.deletePassword(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/updatePassword/{id}")
	public AccountPassword updatePassword(@PathVariable("id") Long id, @RequestBody AccountPassword accountPassword) {
		return accountPasswordService.resetPassword(id, accountPassword);
	}
	
	@GetMapping("/password-by-id/{id}")
	public ResponseEntity<AccountPassword> getPasswordById(@PathVariable("id") Long id){
		AccountPassword password = accountPasswordService.findPasswordById(id);
		return new ResponseEntity<>(password,HttpStatus.OK);
	}
	
	
	

}

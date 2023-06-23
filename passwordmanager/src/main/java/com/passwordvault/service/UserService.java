package com.passwordvault.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.passwordvault.entity.User;
import com.passwordvault.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	public User addUser(User user) throws Exception {
		User localUser =  this.userRepo.findByEmail(user.getEmail());
		
		if(localUser!= null) {
			System.out.println("User already present");
			throw new Exception("This user with same email exists");
		}
		else {
			localUser = this.userRepo.save(user);
		}
		
		return localUser;
	
	}
	
	public User getUser(String email) {
		return this.userRepo.findByEmail(email);
	}
	
	public void deleteUser(String email) {
		this.userRepo.deleteByEmail(email);
	}
	
}

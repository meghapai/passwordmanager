package com.passwordvault.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.passwordvault.entity.User;
import com.passwordvault.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/register")
	public User createUser(@RequestBody User user) throws Exception {
		//encoding with bcrypt
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		return this.userService.addUser(user);
	}
	
	@GetMapping("/login/{email}")
	public User getUser(@PathVariable("email")String email) {
		return this.userService.getUser(email);
	}
	
	@DeleteMapping("/remove/{email}")
	public ResponseEntity<?> deleteUser(@PathVariable("email")String email){
		this.userService.deleteUser(email);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

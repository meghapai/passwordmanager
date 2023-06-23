package com.passwordvault.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.passwordvault.config.JwtUtils;
import com.passwordvault.entity.JwtRequest;
import com.passwordvault.entity.JwtResponse;
import com.passwordvault.entity.User;
import com.passwordvault.service.UserDetailServiceImpl;

@RestController
@CrossOrigin
public class AuthenticateController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailServiceImpl userDetailService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	//generate token
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		try {
			authenticate(jwtRequest.getEmail(),jwtRequest.getPassword());
		} catch (UsernameNotFoundException e) {
			throw new Exception("User not found");
		}
		
		//authenticate
		UserDetails userDetails =  this.userDetailService.loadUserByUsername(jwtRequest.getEmail());
		String token =  this.jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void authenticate(String email, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
			
		} catch (DisabledException e) {
			throw new Exception("USER DISABLED");
		}
		catch (BadCredentialsException e) {
			throw new Exception("Invalid Credentials");
		}
	}
	
	//return the detail of current user
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		return ((User) this.userDetailService.loadUserByUsername(principal.getName()));
	}
}

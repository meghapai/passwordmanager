package com.passwordvault.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.passwordvault.entity.User;

@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User,Long> {
	
	public User findByEmail(String email);

	public void deleteByEmail(String email);

}

package com.passwordvault.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class AccountPassword {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long passwordId;
	private String accountname;
	private String username;
	private String accPassword;
	private String userEmail;
	
}

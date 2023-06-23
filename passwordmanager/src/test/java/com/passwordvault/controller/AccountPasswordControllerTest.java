package com.passwordvault.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.passwordvault.config.JwtAuthenticationEntryPoint;
import com.passwordvault.config.JwtAuthenticationFilter;
import com.passwordvault.config.JwtUtils;
import com.passwordvault.entity.AccountPassword;
import com.passwordvault.repo.AccountRepo;
import com.passwordvault.service.AccountPasswordService;
import com.passwordvault.service.UserDetailServiceImpl;

@WebMvcTest(controllers =  AccountPasswordController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AccountPasswordControllerTest {
	
	@MockBean
	UserDetailServiceImpl userDetailServiceImpl;
	
	@MockBean
	JwtUtils jwtUtils;
	
	@MockBean
	JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@MockBean
	JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper mapper;
	
	
	@MockBean
	AccountRepo accountRepo;
	
	@MockBean
	AccountPasswordService accountPasswordService;
	
	AccountPassword accountPassword1 = new AccountPassword(2L,"Facebook","mp@facebook","mpfb","paimegha@gmail.com");
	AccountPassword accountPassword2 = new AccountPassword(3L,"Insta","meg@insta","1234","paimegha@gmail.com");
	AccountPassword accountPassword3 = new AccountPassword(4L,"Facebook","rp@facebook","mpfb","rimapai@gmail.com");
	
	@Test
	public void getAllPasswords_success() throws Exception{
		List<AccountPassword> accountPasswords = new ArrayList<>(Arrays.asList(accountPassword1,accountPassword2,accountPassword3));
		
		Mockito.when(accountPasswordService.findAllPasswords()).thenReturn(accountPasswords);
		mockMvc.perform(MockMvcRequestBuilders.get("/accountPassword/all")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
				
	}
	
	@Test
	public void getPasswordByEmail_success()throws Exception{
		Mockito.when(accountPasswordService.findAllPasswordsByEmail(accountPassword1.getUserEmail())).thenReturn(java.util.List.of(accountPassword1));;
		mockMvc.perform(MockMvcRequestBuilders.get("/accountPassword/find-password/paimegha@gmail.com")
				.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
		
	}
	
	@Test
	public void addPassword_success() throws Exception{
		AccountPassword password = new AccountPassword(5l,"Facebook","mp@facebook","mpfb","meghapai@gmail.com");
		Mockito.when(accountPasswordService.addPassword(password)).thenReturn(password);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/accountPassword/add-password")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content(this.mapper.writeValueAsString(password));
		
	    mockMvc.perform(mockRequest)
        .andExpect(status().isCreated());
	}
	
	
	@Test
	public void updatePassword() throws Exception {
	    AccountPassword password = new AccountPassword(null,"Facebook","mp@facebook","mpfb","meghapai@gmail.com");

	    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/accountPassword/updatePassword/5")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content(this.mapper.writeValueAsString(password));

	    mockMvc.perform(mockRequest)
	            .andExpect(status().isOk());
	    }

	@Test
	public void deletePassword() throws Exception{
		Mockito.when(accountPasswordService.findPasswordById(accountPassword3.getPasswordId())).thenReturn(accountPassword3);
		 mockMvc.perform(MockMvcRequestBuilders
		            .delete("/accountPassword//delete/3")
		            .contentType(MediaType.APPLICATION_JSON))
		            .andExpect(status().isOk());
	}


	
	
}

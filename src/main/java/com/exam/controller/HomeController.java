package com.exam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.dto.MemberDTO;
import com.exam.service.MemberService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HomeController {

	
	 @GetMapping("/home")
	 public String home() {
		 return "home 2";
	 }
	 
	 @GetMapping("/hello-world")
	 public String helloworld() {
		 return "hello-world";
	 }
	 
}




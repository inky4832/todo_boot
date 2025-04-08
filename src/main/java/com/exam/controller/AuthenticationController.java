package com.exam.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.security.JwtTokenProvider;
import com.exam.security.JwtTokenResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AuthenticationController {

	/*
	   로그인 요청은 반드시 POST
	 
	   로그인 요청: 
	     header정보: Content-Type: application/json
	     
         {
            id:"kim4832",
	        pw:"1234"
 
	      }


       응답:
         {
            token: 암호화된토큰값 <== 내부적으로 사용자id+pw+추가하고자하는임의값
	        userid:inky4832
	      }

	 
	*/
	JwtTokenProvider tokenProvider;
	
	public AuthenticationController(JwtTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}


	@PostMapping("/authenticate")
	public ResponseEntity<JwtTokenResponse> authenticate(@RequestBody Map<String, String> map) {
		
		String token  = tokenProvider.authenticate(map);
		
		if(token != null ) {
			
		   JwtTokenResponse response = 
				new JwtTokenResponse(token, map.get("userid"));
		
		   return ResponseEntity.status(200).body(response);
		}else {
		   return new ResponseEntity("로그인 실패", HttpStatus.BAD_REQUEST);  // 로그인 실패시 400 반환
		}
	}
	
}




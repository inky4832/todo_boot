package com.exam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class MemberController {

	MemberService service;
	public MemberController(MemberService service) {
		this.service = service;
	}

	//회원가입
	/*
	    Talend API 에서 다음과 같이 JSON 요청하자.
	    POST 요청
	    header값: Content-type:application/json
	     {
	        "userid":"kim4832",
	        "passwd":"1234",
	        "username":"김유신"
	      }
	     위 JSON을 자바의 MemberDTO 에 저장됨. 
	 */
	 @PostMapping("/signup")
	 public ResponseEntity<MemberDTO> save(@Valid  @RequestBody MemberDTO dto) {
		 
		 // userid 중복체크
		// if(searchDTO == null ) {
			 log.info("비번 암호화전: {}", dto.getPasswd());
			 //비번을 암호화 해야됨.
			 String encodedPW = new BCryptPasswordEncoder().encode(dto.getPasswd());
			 log.info("비번 암호화후: {}", encodedPW);
			 
			 dto.setPasswd(encodedPW);
			 int n = service.save(dto);
			 
			 return ResponseEntity.created(null).body(dto);  // 201 상태코드 반환됨.
		
	 }
	 
	 //로그인 이후(인증후)의 요청
	 /*
	     로그인후에 얻은 token 값을 항상 header에 저장해서 전달해야 된다.
	     
	     Authorization:  Bearer eyJraWQiOiIwMzljZGZlZi0wMDY3LTQ0MGYtOGIyNi03NDY1NzZlOTcyNTUiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiTWVtYmVyRFRPKHVzZXJpZD1raW00ODMyLCBwYXNzd2Q9MTIzNCwgdXNlcm5hbWU96rmA7Jyg7IugKSIsImV4cCI6MTczODY2MDM4OCwiaWF0IjoxNzM4NjU0OTg4LCJzY29wZSI6IlVTRVIifQ.MkB9L4Cu_T02KcrC76P27FWDR4W6Bx5c_juLMMRFOenQ2Yfi8i__hIuQfPsS2Qgt1rH20qVSSNFsewl68v8WnctH3rGHd0Sjqw7-8BMgUNGPJhuM-6f2_7dC5FV3u40_px_bHZBH4-SCh1Tp4FKFKbg5cr-rSP2fxrL7q6J0LlwNr46rWeeNLnlp0eovj417QgE960CpXoT0dRvGaRys6zcKk0GYfXhxgXUaA7JxluBZkBcJjErgyawMwa9VlxjHKy7pX_DLqu3kT1EsROdl7r1cx7nLYe6BgpBkGquQFomLqH5xg7zvPcbII3kt72DoHz4Hmwz877zXeBmgqF0_1Q
	 
	 
	 */
	 @GetMapping("/mypage")
	 public ResponseEntity<MemberDTO> mypage() {
		 
		 /*
		     JwtTokenProvider 에서 다음과 같이 userid 로 저장하자.
		     
		     token = new UsernamePasswordAuthenticationToken(userid, null, authorities);
		  */
		 
		 Authentication authentication = 
					SecurityContextHolder.getContext().getAuthentication();
			log.info("logger:Authentication: {}", authentication); 
			log.info("logger:Authentication.getName: {}", authentication.getName());

		    String userid = authentication.getName();
		    
		    MemberDTO dto = service.findById(userid);
		    
		    return ResponseEntity.status(200).body(dto);
		 
	 }
	 
	 
}




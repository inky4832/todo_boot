package com.exam.security;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.exam.dto.MemberDTO;
import com.exam.service.MemberService;

//용도: 입력된 id와 pw 이용해서 DB와 연동해서 검증작업후 최종적으로  token 반환해주는 역할.

@Component
public class JwtTokenProvider {

	MemberService memberService;
	JwtTokenService tokenService;
	
	public JwtTokenProvider(MemberService memberService, JwtTokenService tokenService) {
		this.memberService = memberService;
		this.tokenService = tokenService;
	}


	public String authenticate(Map<String, String> map) {
		String encodedtoken=null;
		
		String userid = map.get("userid");
		String passwd = map.get("passwd");
		
		MemberDTO dto = memberService.findById(userid);
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		UsernamePasswordAuthenticationToken token = null;
		if( dto != null && passwordEncoder.matches(passwd, dto.getPasswd())) {
			
			MemberDTO new_dto = new MemberDTO();
			new_dto.setUserid(userid);
			new_dto.setPasswd(passwd); // 1234
			new_dto.setUsername(dto.getUsername());
			
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new  SimpleGrantedAuthority("USER")); 
			
			// 다음 token 정보가 세션에 저장된다.
//			token = new UsernamePasswordAuthenticationToken(new_dto, null, authorities);
			token = new UsernamePasswordAuthenticationToken(userid, null, authorities);

			// Authentication 을 이용해서 token 생성
			encodedtoken = tokenService.generateToken(token);
			
		}//end if
		
				
		return encodedtoken;
	}
}

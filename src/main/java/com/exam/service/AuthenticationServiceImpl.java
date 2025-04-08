package com.exam.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.exam.dto.MemberDTO;
import com.exam.mapper.MemberMapper;

// 로그인만 구현하자.
@Service
public class AuthenticationServiceImpl implements AuthenticationService{

	MemberMapper mapper;
	
	public AuthenticationServiceImpl(MemberMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public MemberDTO authenticate(Map<String, String> map) {
		return mapper.authenticate(map);
	}
}

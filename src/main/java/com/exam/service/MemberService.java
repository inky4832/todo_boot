package com.exam.service;

import com.exam.dto.MemberDTO;

// 회원가입과 mypage만 구현하자.
public interface MemberService {
	public int save(MemberDTO dto);
	public MemberDTO findById(String userid);
}

package com.exam.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.exam.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	public int save(MemberDTO dto);
	public MemberDTO authenticate(Map<String, String> map);
	public MemberDTO findById(String userid);
	
}

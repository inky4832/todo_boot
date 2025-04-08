package com.exam.dto;

import org.apache.ibatis.type.Alias;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Alias("MemberDTO")
public class MemberDTO {

	@NotBlank(message = "userid 필수")
	String userid;

	String passwd;
	
	@NotBlank(message = "username 필수")
	String username;
	
}

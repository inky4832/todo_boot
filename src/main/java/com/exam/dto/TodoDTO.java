package com.exam.dto;

import java.time.LocalDate;

import org.apache.ibatis.type.Alias;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Alias("TodoDTO")
public class TodoDTO {

	 private int id;
	 private String userid;

	 @Size(min = 2, message = "description 항목은 최소 2글자")
	 private String description;
	 
	 @NotNull(message = "목표 날짜는 필수")
	 @Future(message = "현재 날짜보다 미래 날짜 입력")   
	 private LocalDate targetDate; 
	 
	 private boolean done;
	 
	
}

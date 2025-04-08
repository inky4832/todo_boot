package com.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.exam.dto.TodoDTO;

@Mapper
public interface TodoMapper {

	public List<TodoDTO> findAll(String userid);
	public int update(TodoDTO todo);
	public int save(TodoDTO todo);
	public int remove(long id);
	public TodoDTO findById(long id);
}

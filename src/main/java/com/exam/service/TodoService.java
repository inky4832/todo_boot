package com.exam.service;

import java.util.List;

import com.exam.dto.TodoDTO;

public interface TodoService {

	public TodoDTO save(TodoDTO todo);
	public void update(TodoDTO todo);
	public void remove(long id);
	public TodoDTO findById(long id);
	public List<TodoDTO> findAll(String userid);
	
	
}

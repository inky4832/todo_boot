package com.exam.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.exam.dto.TodoDTO;
import com.exam.mapper.TodoMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TodoServiceImpl implements TodoService {

	
	TodoMapper todoMapper;
	
	public TodoServiceImpl(TodoMapper todoMapper) {
		log.info("logger:{}", "TodoServiceImpl");
		this.todoMapper = todoMapper;
	}

	@Override
	public TodoDTO save(TodoDTO todo) {
		int n = todoMapper.save( todo);
		return todo;
	}

	@Override
	public void remove(long id) {
		todoMapper.remove(id);
	}

	@Override
	public TodoDTO findById(long id) {
		return todoMapper.findById(id);
	}

	@Override
	public List<TodoDTO> findAll(String userid) {
		return todoMapper.findAll(userid);
	}

	@Override
	public void update(TodoDTO todo) {
		todoMapper.update(todo);
	}
	
}

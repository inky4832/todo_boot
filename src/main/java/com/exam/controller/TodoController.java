package com.exam.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.dto.MemberDTO;
import com.exam.dto.TodoDTO;
import com.exam.service.TodoService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class TodoController {


	TodoService todoMyBatisService;
	
	public TodoController(TodoService todoMyBatisService) {
		this.todoMyBatisService = todoMyBatisService;
	}
	
	 
	 /*
	     Todo는 모든 요청이 로그인 이후(인증후)의 요청임.
	     따라서 로그인후에 얻은 token 값을 항상 header에 저장해서 전달해야 된다.
	     
	     Authorization:  Bearer eyJraWQiOiIwMzljZGZlZi0wMDY3LTQ0MGYtOGIyNi03NDY1NzZlOTcyNTUiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiTWVtYmVyRFRPKHVzZXJpZD1raW00ODMyLCBwYXNzd2Q9MTIzNCwgdXNlcm5hbWU96rmA7Jyg7IugKSIsImV4cCI6MTczODY2MDM4OCwiaWF0IjoxNzM4NjU0OTg4LCJzY29wZSI6IlVTRVIifQ.MkB9L4Cu_T02KcrC76P27FWDR4W6Bx5c_juLMMRFOenQ2Yfi8i__hIuQfPsS2Qgt1rH20qVSSNFsewl68v8WnctH3rGHd0Sjqw7-8BMgUNGPJhuM-6f2_7dC5FV3u40_px_bHZBH4-SCh1Tp4FKFKbg5cr-rSP2fxrL7q6J0LlwNr46rWeeNLnlp0eovj417QgE960CpXoT0dRvGaRys6zcKk0GYfXhxgXUaA7JxluBZkBcJjErgyawMwa9VlxjHKy7pX_DLqu3kT1EsROdl7r1cx7nLYe6BgpBkGquQFomLqH5xg7zvPcbII3kt72DoHz4Hmwz877zXeBmgqF0_1Q
	 
	 
	 */
	

	// Todo 목록보기
	/*
      Talend API 에서 다음과 같이 JSON 요청하자.
    
      GET: 
        
             http://localhost:8090/app/todos
      
      HEADERS:
      
             Authorization:  Bearer  토큰값

     요청에 대한 응답은 다음과 같다.
	   
	  status: 200
	  body:    [
				{
				"id": 1000,
				"userid": "inky4832",
				"description": "Learn SpringBoot2.1.8",
				"targetDate": "2026-02-21",
				"done": false
				},
				{
				"id": 1001,
				"userid": "inky4832",
				"description": "Learn Reactjs",
				"targetDate": "2025-03-21",
				"done": false
				},
				...
				]

    */
	@GetMapping("/todos")
	public ResponseEntity<List<TodoDTO>> findAll() {
		
		 Authentication authentication = 
					SecurityContextHolder.getContext().getAuthentication();
	
		    String userid = authentication.getName();
		    log.info("logger:findAll: {}", userid);
		    
		List<TodoDTO> todos = todoMyBatisService.findAll(userid);
		
		return ResponseEntity.status(200).body(todos);
	}
	
	// Todo 특정 id에 해당하는  item 보기
	/*
      Talend API 에서 다음과 같이 JSON 요청하자.
    
      GET:  http://localhost:8090/app/todos/1001
      
      HEADERS:
      
             Authorization:  Bearer  토큰값
      
     요청에 대한 응답은 다음과 같다.
	   
	  status: 200
	  body:    {
				"id": 1001,
				"userid": "inky4832",
				"description": "Learn Reactjs",
				"targetDate": "2025-03-21",
				"done": false
				}
    */
	@GetMapping("/todos/{id}")
	public  ResponseEntity<TodoDTO> findById(@PathVariable int id) {
		
		TodoDTO todo =  todoMyBatisService.findById(id); 
		return ResponseEntity.status(200).body(todo);
	}
	
	
	
	// Todo 생성하기
	/*
    Talend API 에서 다음과 같이 JSON 요청하자.
    
    POST: http://localhost:8090/app/todos
    
     HEADERS:
      
             Authorization:  Bearer  토큰값
             Content-type:application/json
     
     BODY:
	       {
	        "description":"Learn SQL",
	        "targetDate":"2030-01-01",
	        "done":false
	       }
     
       TODO의 id값은 auto_increment 로 지정되어 있음.
       
     요청에 대한 응답은 다음과 같다.
	   
	  status: 201
	  body:    {
				"id": 0,
				"userid": "inky4832",
				"description": "Learn SQL",
				"targetDate": "2030-01-01",
				"done": false
				}
 */
	@PostMapping("/todos")
	public ResponseEntity<TodoDTO>  save(@Valid @RequestBody TodoDTO todo) {
		

		 Authentication authentication = 
					SecurityContextHolder.getContext().getAuthentication();
	
		 String userid = authentication.getName();
		 todo.setUserid(userid);
		 
		log.info("LOGGER: createTodo:{}", todo);
		TodoDTO createdTodo = todoMyBatisService.save(todo);
		
		return ResponseEntity.created(null).body(createdTodo);
	}

	// Todo 수정하기
	/*
    
    Talend API 에서 다음과 같이 JSON 요청하자.
    
     PUT : http://localhost:8090/app/todos/1000
     
     HEADERS:
      
             Authorization:  Bearer  토큰값
             Content-type:application/json
     
     BODY:
	         {
		        "description":"Learn SpringBoot3",
		        "targetDate":"2030-01-01",
		        "done":false		        
		     }
     
       TODO의 id값은 auto_increment 로 지정되어 있음.
       
      요청에 대한 응답은 다음과 같다.
	   
	      status: 201
	      body:   {
					"id": 1000,
					"userid": "inky4832",
					"description": "Learn SpringBoot3",
					"targetDate": "2030-01-01",
					"done": false
					}
 */
	
	@PutMapping("/todos/{id}")
	public ResponseEntity<TodoDTO> update(@PathVariable int id, @Valid @RequestBody TodoDTO todo) {

		Authentication authentication = 
					SecurityContextHolder.getContext().getAuthentication();
	
		String userid = authentication.getName();
		todo.setId(id);
		todo.setUserid(userid);
		todoMyBatisService.update(todo);
		return ResponseEntity.status(201).body(todo);  // 생성과 동일하게 수정도 201 + 수정된 데이터 포함하여 반환
	}
	
	// Todo 삭제하기
	/*
	    Talend API 에서 다음과 같이 JSON 요청하자.
	  
	    DELETE:  http://localhost:8090/app/todos/1001
	    
	    HEADERS:
	    
	           Authorization:  Bearer  토큰값
	           
	   
	   요청에 대한 응답은 다음과 같다.
	   
	      status: 204
	      body:   없음
	    
	  */
		@DeleteMapping("/todos/{id}")
		public ResponseEntity<Void> remove(@PathVariable int id) {
			
			log.info("LOGGER: deleteTodo:{}", id);

			todoMyBatisService.remove(id);
			return ResponseEntity.noContent().build(); // status는 204, 요청이 성공했으나 응답은 없음을 의미.
		}
	
}

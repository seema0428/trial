package com.todoitem.todoitem.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todoitem.todoitem.exception.*;
import com.todoitem.todoitem.model.Status;
import com.todoitem.todoitem.model.TodoItem;
import com.todoitem.todoitem.repository.TodoRepository;

@RestController
@RequestMapping("/api")
public class TodoItemController {
	
	@Autowired
	private TodoRepository todoItemRepository;

	/*"Return all todoItems available in the System option with status not done."*/
	@GetMapping("/todosItems")
	public  ResponseEntity<List<TodoItem>> getAllTodoItems(@RequestParam(required = false)  Status status){
		try {
			List<TodoItem>todoList= new ArrayList<>();
		if(status==Status.NOTE_DONE) 
			todoItemRepository.findByStatusNotDone(status).forEach(todoList::add);
		else 
			todoItemRepository.findAll().forEach(todoList::add);
			
		if(todoList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(todoList, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*"add new  todoItem into the System."*/
	@PostMapping("/addTodoItems")
	public ResponseEntity<TodoItem> addTodoItem(@RequestBody TodoItem todoItem) {
		try {
			TodoItem newTodoItem = todoItemRepository.save(todoItem);		
			return new ResponseEntity<>(newTodoItem, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		  
	}
	
	/*"Return todoItems available in the System  by id."*/
	@GetMapping("/getTodoItemsById/{id}")
	public ResponseEntity<TodoItem> getTodoItemById(@PathVariable Long id) throws ResourceNotFoundException {
		TodoItem todoItem = todoItemRepository.findById(id)
				.orElseThrow(() ->new ResourceNotFoundException("TodoItem not found for this id :: " + id));
		return new ResponseEntity<>(todoItem, HttpStatus.FOUND);
	}
	
	/*"update status of todoItems based  on it ."*/
   @PutMapping("/updateStatus/{id}/{status}")
   public ResponseEntity<?> updateStatus(@RequestParam Long id, @RequestParam Status status) throws ResourceNotFoundException{
	   TodoItem updateTodoItem = todoItemRepository.findById(id)
				.orElseThrow(() ->new ResourceNotFoundException("TodoItem not found for this id :: " + id));
	   if(updateTodoItem!=null) {
		  updateTodoItem.setStatus(status);
		  todoItemRepository.save(updateTodoItem);
	   }
	   return new ResponseEntity<>(updateTodoItem, HttpStatus.OK); 
	   
	}
   /*"update discription of todoItems based on id."*/
   @PutMapping("/updateDiscription/{id}/{discription}")
   public ResponseEntity<?>updateDiscription(@RequestParam Long id, @RequestParam String discription) throws ResourceNotFoundException{
	   //int updateDiscription =0;
	   TodoItem updateItemDiscription = todoItemRepository.findById(id)
				.orElseThrow(() ->new ResourceNotFoundException("TodoItem not found for this id :: " + id));
	  if(updateItemDiscription!=null) {
		  updateItemDiscription.setDiscription(discription);
	      todoItemRepository.save(updateItemDiscription);
	  }
	   return new ResponseEntity<>(updateItemDiscription, HttpStatus.OK); 
   }
   

}

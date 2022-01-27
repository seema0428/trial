package com.todoitem.todoitem;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.todoitem.todoitem.controller.TodoItemController;
import com.todoitem.todoitem.exception.ResourceNotFoundException;
import com.todoitem.todoitem.model.Status;
import com.todoitem.todoitem.model.TodoItem;
import com.todoitem.todoitem.repository.TodoRepository;

@SpringBootTest(classes = {TodoItemControllerUnitTest.class})
class TodoItemControllerUnitTest {

	@Mock
	TodoRepository todoRepository;
	
	@InjectMocks
	TodoItemController todoItemController;

	TodoItem mockItem;
	 List<TodoItem> todoItems ;
	
	@Test
	void test_findByID() throws ResourceNotFoundException {
		mockItem= new TodoItem(2L,"Buy Milk", Status.DONE,LocalDateTime.of(2021, Month.DECEMBER, 22, 14, 33)
				,LocalDateTime.of(2021, Month.DECEMBER, 23, 14, 33), LocalDateTime.of(2021, Month.DECEMBER, 23, 14, 33));
	
		Long itemId=2L;
		Mockito.when(todoRepository.findById(itemId)).thenReturn(Optional.of(mockItem));
		ResponseEntity<TodoItem>res =todoItemController.getTodoItemById(itemId);
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(itemId, res.getBody().getId());
	}
	
	@Test
	void test_findAll_with_status_not_done_not_manadatory() {
		Status status = null;
		todoItems = new ArrayList<TodoItem>();
		todoItems.add(new TodoItem(2L,"Buy Milk", Status.DONE,LocalDateTime.of(2021, Month.DECEMBER, 22, 14, 33)
				,LocalDateTime.of(2021, Month.DECEMBER, 23, 14, 33), LocalDateTime.of(2021, Month.DECEMBER, 23, 14, 33)));
    	todoItems.add(new TodoItem(3L,"Buy Milk", Status.NOTE_DONE,LocalDateTime.of(2021, Month.DECEMBER, 22, 14, 33)
				,LocalDateTime.of(2021, Month.DECEMBER, 23, 14, 33), LocalDateTime.of(2021, Month.DECEMBER, 23, 14, 33)));
    	Mockito.when(todoRepository.findAll()).thenReturn(todoItems);
    	ResponseEntity<List<TodoItem>>res =todoItemController.getAllTodoItems(status);
    	assertEquals(HttpStatus.OK,res.getStatusCode());
	}

}

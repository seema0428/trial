package com.todoitem.todoitem;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todoitem.todoitem.controller.TodoItemController;
import com.todoitem.todoitem.model.Status;
import com.todoitem.todoitem.model.TodoItem;
import com.todoitem.todoitem.repository.TodoRepository;

@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages="com.todoitem.todoitem")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {TodoItemControllerIntegrationTest.class})
class TodoItemControllerIntegrationTest { 
	
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	TodoRepository todoRepository;
	
	@InjectMocks
	TodoItemController todoItemController;

	TodoItem mockItem;
	ObjectMapper mapper =new ObjectMapper();
	

	@BeforeEach
	public void setUp() {
		mockMvc =MockMvcBuilders.standaloneSetup(todoItemController).build();
		
	}
	
	@Test
	@Order(1)
	@DisplayName("Test findById with valid input")
	void test_test_findById() throws Exception  {
		mockItem= new TodoItem(2L,"Buy Milk", Status.DONE,LocalDateTime.of(2021, Month.DECEMBER, 22, 14, 33)
				,LocalDateTime.of(2021, Month.DECEMBER, 23, 14, 33), LocalDateTime.of(2021, Month.DECEMBER, 23, 14, 33));
	
		Long itemId=2L;
		Mockito.when(todoRepository.findById(itemId)).thenReturn(Optional.of(mockItem));
		this.mockMvc.perform(get("/api/getTodoItemsById/{id}",itemId))
		.andExpect(status().isFound())
		.andDo(print())
		.andExpect(MockMvcResultMatchers.jsonPath(".id").value(2));
	}

	

	@Test
	@Order(2)
	@DisplayName("Test findById() with invalid itemId: itemId has the string value as input")
	public void findById_WhenIdIsInValid_ReturnError() throws Exception {
	     Mockito.when(todoRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
	      mockMvc.perform(get("/api/getTodoItemsById/{id}","aa")
	              .accept(MediaType.APPLICATION_JSON))
	              .andExpect(status().isBadRequest());
   }
		
	@Test
	@Order(3)
	@DisplayName("Test createTodoItem with Invalid date input:( with date value)")
	public void test_addNewTodoItem() throws Exception  {
			Map<String,Object> createTodoItemRequest = new HashMap<>();
			createTodoItemRequest.put("discription","Buy Milk");
			createTodoItemRequest.put("status",Status.DONE);
			createTodoItemRequest.put("createdDate","bad-date-str");
			createTodoItemRequest.put("dueDate","bad-date-str");
			createTodoItemRequest.put("markDateAsDone","bad-date-str");
			Mockito.when(todoRepository.save(mockItem)).thenReturn(mockItem);
			String jsonBody=mapper.writeValueAsString(createTodoItemRequest);
			this.mockMvc.perform(post("/api/addTodoItems").content(jsonBody)
						.contentType(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(status().isBadRequest());						
		}	
	
	@Test
	@Order(4)
	@DisplayName("Test updateDiscription with invalid input:id did not send in  request param.")
	public void test_updateDiscription() throws Exception  {
		mockItem= new TodoItem(4L,"Buy Milk", Status.DONE,LocalDateTime.of(2021, Month.DECEMBER, 22, 14, 33)
				,LocalDateTime.of(2021, Month.DECEMBER, 23, 14, 33), LocalDateTime.of(2021, Month.DECEMBER, 23, 14, 33));
			Long itemId =4L;
			String discription ="read books";
			Mockito.when(todoRepository.findById(itemId)).thenReturn(Optional.of(mockItem));
			Mockito.when(todoRepository.save(mockItem)).thenReturn(mockItem);
			ObjectMapper mapper =new ObjectMapper();
			String jsonBody=mapper.writeValueAsString(mockItem);
			this.mockMvc.perform(put("/api/updateDiscription/{discription}",discription)
					 	.content(jsonBody)
						.contentType(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(status().isNotFound());	
		}	
	
}

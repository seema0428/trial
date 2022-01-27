package com.todoitem.todoitem;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.todoitem.todoitem.model.Status;
import com.todoitem.todoitem.model.TodoItem;
import com.todoitem.todoitem.repository.TodoRepository;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TodoItemRepositoryTest {
 
    @Autowired
    private TodoRepository todoRepository;
    List<TodoItem> todoItem ;
    
    @Test
    @Rollback(value = false)
    public void testSaveNewTodoItem() {
    	
    	 todoItem= Arrays.asList( new TodoItem(2L,"Buy Milk", Status.DONE,LocalDateTime.of(2021, Month.DECEMBER, 22, 14, 33)
				,LocalDateTime.of(2021, Month.DECEMBER, 23, 14, 33), LocalDateTime.of(2021, Month.DECEMBER, 23, 14, 33)));
    	
    	
    	Iterable<TodoItem> allTodoItems = todoRepository.saveAll(todoItem);

        AtomicInteger validIdFound = new AtomicInteger();
        allTodoItems.forEach(item -> {
            if(item.getId()>0){
                validIdFound.getAndIncrement();
            }
        });

        assertThat(validIdFound.intValue()).isEqualTo(1);
    	}
      
    }


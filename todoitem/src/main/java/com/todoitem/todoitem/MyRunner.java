package com.todoitem.todoitem;


import java.time.LocalDateTime;
import java.time.Month;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.todoitem.todoitem.model.Status;
import com.todoitem.todoitem.model.TodoItem;
import com.todoitem.todoitem.repository.TodoRepository;

@Component
public class MyRunner implements CommandLineRunner {

	

	@Autowired
	private TodoRepository todoRepository;
	
	private static final Logger log = LoggerFactory.getLogger(MyRunner.class);
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		todoRepository.save( new TodoItem(1L,"Buy Milk", Status.DONE,LocalDateTime.of(2021, Month.DECEMBER, 22, 14, 33)
				,LocalDateTime.of(2021, Month.DECEMBER, 23, 14, 33), LocalDateTime.of(2021, Month.DECEMBER, 23, 14, 33)));
		todoRepository.save( new TodoItem(2L,"Buy Milk", Status.NOTE_DONE,LocalDateTime.of(2021, Month.DECEMBER, 23, 14, 33)
				,LocalDateTime.of(2021, Month.DECEMBER, 24, 14, 33), LocalDateTime.of(2021, Month.DECEMBER, 24, 14, 33)));	
		// fetch all TodoTasks
	        log.info("TodoItem found with complete status):");
	        log.info("-------------------------------");
	        for (TodoItem todoItem : todoRepository.findAll()) {
	            log.info(todoItem.toString());
	        }
	        log.info("");

		}
	}



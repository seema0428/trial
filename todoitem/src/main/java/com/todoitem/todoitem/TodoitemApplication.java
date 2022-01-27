package com.todoitem.todoitem;

import java.time.LocalDate;
import java.time.Month;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import com.todoitem.todoitem.model.Status;
import com.todoitem.todoitem.model.TodoItem;





@SpringBootApplication
@EntityScan("com.todoitem.todoitem.model")
@EnableScheduling
public class TodoitemApplication  {

	
	private static final Logger log = LoggerFactory.getLogger(TodoitemApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(TodoitemApplication.class, args);
	}
	
	
		
}

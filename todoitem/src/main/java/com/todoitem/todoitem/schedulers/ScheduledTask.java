package com.todoitem.todoitem.schedulers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.todoitem.todoitem.model.Status;
import com.todoitem.todoitem.repository.TodoRepository;

@Component
public class ScheduledTask {

	
	@Autowired
	private TodoRepository todoRepository;
	
	/**
     * Method for  updating status past_due if dueDate is over
     * runs every 15 minutes, but can run this schedule task for weekly, monthly yearly using @Scheduled(cron="* * * * *")
     * this is here 15 minutes for demo purpose 
     */
    @Scheduled(fixedRate = 900000)
    public void updateStatusAfterDueDate() {
		Status pastDue = Status.PAST_DUE;
		Status done = Status.DONE;
		LocalDateTime todaysDate = LocalDateTime.now();
		int result=todoRepository.updateByStatusPastDue(pastDue,todaysDate, done);
		}
}

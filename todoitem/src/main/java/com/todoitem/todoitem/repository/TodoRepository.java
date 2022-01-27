package com.todoitem.todoitem.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.todoitem.todoitem.model.Status;
import com.todoitem.todoitem.model.TodoItem;

@Repository
public interface TodoRepository extends JpaRepository<TodoItem, Long>  {

	
	List<TodoItem> findAll();
	
	@Query("select todo from TodoItem todo where todo.status =:status")
	List<TodoItem>findByStatusNotDone(@Param ("status")Status status);

	@Transactional
	@Modifying
	@Query("update TodoItem  todo set todo.status =:pastDue where todo.dueDate < :now and todo.status NOT IN(:done)")
	int updateByStatusPastDue( Status pastDue,LocalDateTime now, Status done);

} 

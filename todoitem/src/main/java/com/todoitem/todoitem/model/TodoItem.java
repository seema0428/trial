package com.todoitem.todoitem.model;


import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table
public class TodoItem {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@NotNull
	private Long id;
	
	@Column(name="DISCRIPTION")
	private String discription;
	
	@Column(name="STATUS")
	private Status status;
	
	@Column(name ="DATE_CREATED")
	private  LocalDateTime dateCreated;
	
	@Column(name ="DUE_DATE")
	private LocalDateTime dueDate;
	
	@Column(name ="DATE_ITEM_MARKED_AS_STATUSDONE")
	private LocalDateTime date_Item_Marked_Status_AsDone;
	
	

	public TodoItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TodoItem(Long id, String discription, Status status, LocalDateTime dateCreated, LocalDateTime dueDate,
			LocalDateTime date_Item_Marked_Status_AsDone) {
		super();
		this.id = id;
		this.discription = discription;
		this.status = status;
		this.dateCreated = dateCreated;
		this.dueDate = dueDate;
		this.date_Item_Marked_Status_AsDone = date_Item_Marked_Status_AsDone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDateTime getDate_Item_Marked_Status_AsDone() {
		return date_Item_Marked_Status_AsDone;
	}

	public void setDate_Item_Marked_Status_AsDone(LocalDateTime date_Item_Marked_Status_AsDone) {
		this.date_Item_Marked_Status_AsDone = date_Item_Marked_Status_AsDone;
		}
	
	}

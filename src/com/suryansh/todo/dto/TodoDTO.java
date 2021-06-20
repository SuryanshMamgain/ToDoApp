package com.suryansh.todo.dto;
import com.suryansh.todo.utils.Constants;

import java.io.Serializable;
import java.util.Date;

public class TodoDTO implements Serializable {
	private String name;
	private String desc;
	private Date date;
	private String status;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private TodoDTO(){
		date=new Date();
		status=Constants.PENDING;
		
	}
	@Override
	public String toString() {
		return "TodoDTO [name=" + name + ", desc=" + desc + ", date=" + date + ", status=" + status + "]";
	}
	public TodoDTO(String name,String desc) {
		this();
		this.name=name;
		this.desc=desc;
	}
}

package com.jeff.testdb;

import java.util.Date;

public class Row {
	
	private String name;
	private Date date;
	private String info;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	public String toString() {
		
		return "Name: " + name + " Date: " + date + " Info: " + info;
	}
	

}

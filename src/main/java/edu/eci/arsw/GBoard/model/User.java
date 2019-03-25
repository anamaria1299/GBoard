package edu.eci.arsw.GBoard.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

public class User implements Serializable{

	private Long id;
	private String name;
	private String lastName;
	private String nickName;
	private String password;
	private Date initialDate;
	
	public User() {}
	public User(String name, String lastName, String nickName, String password) {
		this.name= name;
		this.lastName= lastName;
		this.nickName= nickName;
		this.password= password;
		this.initialDate= new Date(Calendar.getInstance().getTime().getTime());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}
}

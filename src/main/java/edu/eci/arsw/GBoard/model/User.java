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
	private Date lastDate;
	private String gender;
	private String webPage;
	private String email;
	private String country;
	private String profile;
	
	public User() {}
	public User(String name, String lastName, String nickName, String password) {
		this.name= name;
		this.lastName= lastName;
		this.nickName= nickName;
		this.password= password;
		this.initialDate= new Date(Calendar.getInstance().getTime().getTime());
		this.lastDate= new Date(Calendar.getInstance().getTime().getTime());
	}

	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
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
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getWebPage() {
		return webPage;
	}
	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lastName=" + lastName + ", nickName=" + nickName + ", password="
				+ password + ", initialDate=" + initialDate + ", lastDate=" + lastDate + ", gender=" + gender
				+ ", webPage=" + webPage + ", email=" + email + ", country=" + country + ", profile=" + profile + "]";
	}
}

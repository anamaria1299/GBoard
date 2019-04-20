package edu.eci.arsw.GBoard.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class Room implements Serializable{

	private Long id;
	private String title;
	private User owner;
	private ArrayList<User> members;
	private Date creationDate;
	private ArrayList<Tag> tags;
	private RoomType type;
	private int NUMBERMEMBERS;
	private String password;
	
	public Room() {}
	public Room(String title, User owner, ArrayList<User> members, ArrayList<Tag> tags, RoomType type, String password) {
		this.title= title;
		this.owner= owner;
		this.members= members;
		this.tags= tags;
		this.type= type;
		this.password= password;
		this.creationDate= new Date(Calendar.getInstance().getTime().getTime());
		this.NUMBERMEMBERS= members.size();
	}

	public ArrayList<Tag> getTags() {
		return tags;
	}
	public void setTags(ArrayList<Tag> tags) {
		this.tags = tags;
	}
	public RoomType getType() {
		return type;
	}
	public void setType(RoomType type) {
		this.type = type;
	}
	public int getNumberMembers() {
		return NUMBERMEMBERS;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public ArrayList<User> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<User> members) {
		this.members = members;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
}

package edu.eci.arsw.GBoard.model;

import java.io.Serializable;

public class RoomType implements Serializable{

	private Long id;
	private String roomType;
	
	public RoomType() {}
	public RoomType(String roomType) {
		this.roomType= roomType;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}

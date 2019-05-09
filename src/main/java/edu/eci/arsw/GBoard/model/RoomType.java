package edu.eci.arsw.GBoard.model;

import java.io.Serializable;

public class RoomType implements Serializable{

	private Long id;
	private String roomType;
	
	public RoomType() {}
	public RoomType(String roomType) {
		this.roomType= roomType;
		if(roomType.equals("publica")) {
			this.id=(long)1;
		}else if(roomType.equals("privada")) {
			this.id=(long)2;
		}else {
			this.id=(long)3;
		}
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

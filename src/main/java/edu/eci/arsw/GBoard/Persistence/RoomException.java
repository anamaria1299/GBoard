package edu.eci.arsw.GBoard.Persistence;

public class RoomException extends Exception{
	
	public RoomException(String message) {
		super(message);
	}
	
	public RoomException(String message, Throwable cause) {
        super(message, cause);
    }
}
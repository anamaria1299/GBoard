package edu.eci.arsw.GBoard.Persistence;

public class GBoardException extends Exception{
	
	public GBoardException(String message) {
		super(message);
	}
	
	public GBoardException(String message, Throwable cause) {
        super(message, cause);
    }
}
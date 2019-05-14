package edu.eci.arsw.GBoard.model;

public class Message {

    //private User transmitter;
    private String message;

//    public User getTransmitter() {
//        return transmitter;
//    }
//
//    public void setTransmitter(User transmitter) {
//        this.transmitter = transmitter;
//    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                '}';
    }
}

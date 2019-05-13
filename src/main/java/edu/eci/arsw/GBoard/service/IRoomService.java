package edu.eci.arsw.GBoard.service;

import edu.eci.arsw.GBoard.Persistence.GBoardException;
import edu.eci.arsw.GBoard.model.Room;
import edu.eci.arsw.GBoard.model.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public interface IRoomService {

    List<Room> getRooms() throws GBoardException;

    Room getRoomByTitle(String title) throws GBoardException;

    String save(Room room) throws GBoardException;

    String createRoom(String roomName, String nick) throws GBoardException;

    void updateRoom(Room room) throws GBoardException;

    void addUserToRoom(User user, String room) throws GBoardException;

    String joinRoom(String roomName, String nick) throws GBoardException;

    List<Room> searchProgress(String title) throws GBoardException;

    List<Room> getRoomByOwner(String nickname) throws GBoardException;

    List<Room> getRoomByMember(String nickname) throws GBoardException;
}

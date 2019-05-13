package edu.eci.arsw.GBoard.service.impl;

import edu.eci.arsw.GBoard.Persistence.GBoardException;
import edu.eci.arsw.GBoard.Persistence.Repositories.IRoomRepository;
import edu.eci.arsw.GBoard.Persistence.Repositories.IUserRepository;
import edu.eci.arsw.GBoard.model.Room;
import edu.eci.arsw.GBoard.model.RoomType;
import edu.eci.arsw.GBoard.model.User;
import edu.eci.arsw.GBoard.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Component("room")
public class RoomServiceImpl implements IRoomService {

    @Autowired
    IRoomRepository roomRepository;
    @Autowired
    IUserRepository userRepository;

    @Override
    public List<Room> getRooms() throws GBoardException {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomByTitle(String title) throws GBoardException {
        return roomRepository.find(title);
    }

    @Override
    public String save(Room room) throws GBoardException {
        return roomRepository.save(room);
    }

    @Override
    public String createRoom(HttpServletRequest req, HttpSession session) throws GBoardException {
        String roomName = req.getParameter("createName");
        User user= userRepository.find(session.getAttribute("nick").toString());
        ArrayList<User> users= new ArrayList<>();
        users.add(user);
        RoomType type= new RoomType("publica");
        Room room= new Room(roomName, user, users, null, type, "");
        return roomRepository.save(room);
    }

    @Override
    public void updateRoom(Room room) throws GBoardException {
        roomRepository.upadate(room);
    }

    @Override
    public void addUserToRoom(User user, String room) throws GBoardException {
        roomRepository.addUser(user,room);
    }

    @Override
    public String joinRoom(HttpServletRequest req, HttpSession session) throws GBoardException {
        String roomName = req.getParameter("name");
        roomRepository.addUser(userRepository.find(session.getAttribute("nick").toString()), roomName);
        return roomName;
    }

    @Override
    public List<Room> getRoomByOwner(String nickname) throws GBoardException {
        return roomRepository.findByOwner(nickname);
    }

    @Override
    public List<Room> getRoomByMember(String nickname) throws GBoardException {
        List<Room> all= roomRepository.findAll();
        List<Room> rooms = new ArrayList<Room>();

        for(Room r: all){
            if(!r.getOwner().getNickName().equals(nickname)){
                List<User> users=r.getMembers();
                for(User u: users){
                    if(u.getNickName().equals(nickname)){
                        rooms.add(r);break;
                    }
                }
            }
        }
        return rooms;
    }
}

package edu.eci.arsw.GBoard.service;

import edu.eci.arsw.GBoard.Persistence.GBoardException;
import edu.eci.arsw.GBoard.model.User;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface IUserService {

    List<User> getUsers() throws GBoardException;

    User getUserByNickname(String nickname) throws GBoardException;

    void updateUser(User user) throws GBoardException;

    String createUser(HttpServletRequest rq) throws GBoardException;

    User getCredentials(HttpServletRequest req) throws GBoardException;

}

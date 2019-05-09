package edu.eci.arsw.GBoard.service.impl;

import edu.eci.arsw.GBoard.Persistence.GBoardException;
import edu.eci.arsw.GBoard.Persistence.Repositories.IUserRepository;
import edu.eci.arsw.GBoard.model.User;
import edu.eci.arsw.GBoard.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component("user")
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public List<User> getUsers() throws GBoardException {
        return userRepository.findAll();
    }

    @Override
    public User getUserByNickname(String nickname) throws GBoardException {
        return  userRepository.find(nickname);
    }

    @Override
    public void updateUser(User user) throws GBoardException {
        userRepository.upadate(user);
    }

    @Override
    public String createUser(HttpServletRequest req) throws GBoardException {
        User user = new User();
        user.setName(req.getParameter("inputName"));
        user.setLastName(req.getParameter("inputLast"));
        user.setNickName(req.getParameter("inputNick"));
        user.setPassword(req.getParameter("inputPass"));
        return userRepository.save(user);
    }

    @Override
    public User getCredentials(HttpServletRequest req) throws GBoardException {
        String nick = req.getParameter("inputNick");
        String pass = req.getParameter("inputPass");
        return userRepository.getCredentianls(nick,pass);
    }
}

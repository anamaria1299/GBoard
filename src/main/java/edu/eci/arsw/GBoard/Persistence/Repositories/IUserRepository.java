package edu.eci.arsw.GBoard.Persistence.Repositories;

import org.springframework.stereotype.Repository;

import edu.eci.arsw.GBoard.Persistence.UserException;
import edu.eci.arsw.GBoard.model.User;

@Repository
public interface IUserRepository extends DAO<User, String>{
	
	public User getCredentianls(String nickname, String pass) throws UserException;
}

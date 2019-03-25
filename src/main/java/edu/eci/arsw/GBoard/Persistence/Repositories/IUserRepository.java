package edu.eci.arsw.GBoard.Persistence.Repositories;

import org.springframework.stereotype.Repository;

import edu.eci.arsw.GBoard.model.User;

@Repository
public interface IUserRepository extends DAO<User, Long>{
	
}

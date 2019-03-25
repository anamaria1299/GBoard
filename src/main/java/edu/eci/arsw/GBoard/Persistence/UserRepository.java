package edu.eci.arsw.GBoard.Persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.eci.arsw.GBoard.Persistence.Repositories.IUserRepository;
import edu.eci.arsw.GBoard.config.DataBaseConfiguration;
import edu.eci.arsw.GBoard.model.User;

@Component
public class UserRepository implements IUserRepository{

	@Autowired
	private DataBaseConfiguration database;
	
	@Override
	public List<User> findAll() {
		String query= "select * from users";
		List<User> users= new ArrayList<>();
		Connection connection= null;
		try {
			connection= database.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
		    ResultSet rs = stmt.executeQuery(query);;
		    while (rs.next()) {
		    	User user= new User();
		    	user.setId(rs.getLong("id"));
		    	user.setName(rs.getString("name"));
		    	user.setLastName(rs.getString("lastname"));
		    	user.setNickName(rs.getString("nickname"));
		    	user.setPassword(rs.getString("password"));
		    	user.setInitialDate(rs.getDate("initialdate"));
		    	
		    	users.add(user);
		    }
		    connection.close();
		    return users;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public User find(Long id) {
		
		return null;
	}

	@Override
	public Long save(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void upadate(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	

}

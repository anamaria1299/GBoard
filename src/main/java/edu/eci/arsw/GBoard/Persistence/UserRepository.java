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
public class UserRepository implements IUserRepository {

	@Autowired
	private DataBaseConfiguration database;

	@Override
	public List<User> findAll() {
		String query = "select * from users";
		List<User> users = new ArrayList<>();
		Connection connection = null;
		try {
			connection = database.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setLastName(rs.getString("lastname"));
				user.setNickName(rs.getString("nickname"));
				user.setPassword(rs.getString("password"));
				user.setInitialDate(rs.getDate("initialdate"));
				user.setLastDate(rs.getDate("lastdate"));
				user.setGender(rs.getString("gender"));
				user.setWebPage(rs.getString("webpage"));
				user.setEmail(rs.getString("email"));
				user.setCountry(rs.getString("country"));
				user.setProfile(rs.getString("profile"));
				users.add(user);
			}
			connection.close();
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public User getCredentianls(String nickname, String pass) {
		String query = "select * from users where nickname = '" + nickname + "' and password = '" + pass + "'";
		User user = new User();
		try (Connection connection = database.getDataSource().getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			;
			while (rs.next()) {
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setLastName(rs.getString("lastname"));
				user.setNickName(rs.getString("nickname"));
				user.setPassword(rs.getString("password"));
				user.setInitialDate(rs.getDate("initialdate"));
				user.setLastDate(rs.getDate("lastdate"));
				user.setGender(rs.getString("gender"));
				user.setWebPage(rs.getString("webpage"));
				user.setEmail(rs.getString("email"));
				user.setCountry(rs.getString("country"));
				user.setProfile(rs.getString("profile"));
				return user;
			}
			connection.close();
			throw new UserException("No se ingreso las credenciales bien");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User find(String nickname) {
		String query = "select * from users where nickname = '" + nickname+"'";
		User user = new User();
		try (Connection connection = database.getDataSource().getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			;
			while (rs.next()) {
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setLastName(rs.getString("lastname"));
				user.setNickName(rs.getString("nickname"));
				user.setPassword(rs.getString("password"));
				user.setInitialDate(rs.getDate("initialdate"));
				user.setLastDate(rs.getDate("lastdate"));
				user.setGender(rs.getString("gender"));
				user.setWebPage(rs.getString("webpage"));
				user.setEmail(rs.getString("email"));
				user.setCountry(rs.getString("country"));
				user.setProfile(rs.getString("profile"));
				return user;
			}
			connection.close();
			throw new UserException("No se ingreso las credenciales bien");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String save(User entity) {
		String query = "INSERT INTO \"users\" VALUES ("+entity.getId()+",'"+entity.getName()+"','"+entity.getLastName()+"','"+entity.getNickName()+"','"+entity.getPassword()+"','"+entity.getInitialDate()+"','"+entity.getLastDate()+"',"+entity.getGender()+","+entity.getWebPage()+","+entity.getEmail()+","+entity.getCountry()+","+entity.getProfile()+")";
		Connection connection = null;
		try {
			connection = database.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			stmt.execute(query);
			connection.close();
			return "";
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void upadate(User entity) {
		
		Connection connection = null;
		try {
			connection = database.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			if(entity.getGender()!=null) {
				String query = "update users  set gender='"+entity.getGender()+"' where nickname='"+entity.getNickName()+"'";
				stmt.executeUpdate(query);
			}if(entity.getLastDate()!=null) {
				String query = "update users  set lastdate='"+entity.getLastDate()+"' where nickname='"+entity.getNickName()+"'";
				stmt.executeUpdate(query);
			}if(entity.getPassword()!=null) {
				String query = "update users  set password='"+entity.getPassword()+"' where nickname='"+entity.getNickName()+"'";
				stmt.executeUpdate(query);
			}if(entity.getWebPage()!=null) {
				String query = "update users  set webpage='"+entity.getWebPage()+"' where nickname='"+entity.getNickName()+"'";
				stmt.executeUpdate(query);
			}if(entity.getEmail()!=null) {
				String query = "update users  set email='"+entity.getEmail()+"' where nickname='"+entity.getNickName()+"'";
				stmt.executeUpdate(query);
			}if(entity.getCountry()!=null) {
				String query = "update users  set country='"+entity.getCountry()+"' where nickname='"+entity.getNickName()+"'";
				stmt.executeUpdate(query);
			}if(entity.getProfile()!=null) {
				String query = "update users  set profile='"+entity.getProfile()+"' where nickname='"+entity.getNickName()+"'";
				stmt.executeUpdate(query);
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

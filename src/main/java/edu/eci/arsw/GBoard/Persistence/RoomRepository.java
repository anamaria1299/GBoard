package edu.eci.arsw.GBoard.Persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.eci.arsw.GBoard.Persistence.Repositories.IRoomRepository;
import edu.eci.arsw.GBoard.Persistence.Repositories.ITagRepository;
import edu.eci.arsw.GBoard.Persistence.Repositories.IUserRepository;
import edu.eci.arsw.GBoard.config.DataBaseConfiguration;
import edu.eci.arsw.GBoard.model.Room;
import edu.eci.arsw.GBoard.model.RoomType;
import edu.eci.arsw.GBoard.model.Tag;
import edu.eci.arsw.GBoard.model.User;

@Component
public class RoomRepository implements IRoomRepository{
	
	@Autowired
	private DataBaseConfiguration database;
	@Autowired
	IUserRepository userRepository;
	@Autowired
	ITagRepository tagRepository;

	@Override
	public List<Room> findAll() {
		String query= "select * from room";
		List<Room> rooms= new ArrayList<>();
		Connection connection= null;
		try {
			connection= database.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
		    while (rs.next()) {
		    	Room room= new Room();
		    	room.setId(rs.getLong("id"));
		    	room.setTitle(rs.getString("title"));
		    	room.setOwner(userRepository.find(rs.getString("owner")));
		    	room.setCreationDate(rs.getDate("creationdate"));
		    	room.setPassword(rs.getString("password"));
		    	room.setMembers(new ArrayList<User>());
		    	room.setTags(new ArrayList<Tag>());
		    	RoomType type= new RoomType();
		    	type.setId(rs.getLong("type"));
		    	room.setType(type);
		    	rooms.add(room);
		    }
		    
		    connection.close();
		    
		    for(Room r: rooms) {
		    	ArrayList<User> users= new ArrayList<User>();
		    	query= "select * from user_room where roomid='"+r.getTitle()+"'";
		    	connection= database.getDataSource().getConnection();
				stmt = connection.createStatement();
			    rs = stmt.executeQuery(query);
			    while(rs.next()) {
			    	users.add(userRepository.find(rs.getString("userid")));
			    }
			    r.setMembers(users);
			    connection.close();
		    }
		    
		    for(Room r: rooms) {
		    	ArrayList<Tag> tags= new ArrayList<Tag>();
		    	query= "select * from room_tag where roomid="+r.getId();
		    	connection= database.getDataSource().getConnection();
				stmt = connection.createStatement();
			    rs = stmt.executeQuery(query);
			    while(rs.next()) {
			    	tags.add(tagRepository.find(rs.getLong("tagid"))); 
			    }
			    r.setTags(tags);
			    connection.close();
		    }
		    
		    for(Room r: rooms) {
		    	query= "select * from roomtype where id="+r.getType().getId();
		    	connection= database.getDataSource().getConnection();
		    	stmt = connection.createStatement();
		    	rs = stmt.executeQuery(query);
		    	while(rs.next()) {
		    		r.getType().setRoomType(rs.getString("roomtype")); 
			    }
		    	connection.close();
		    }
		    
		    return rooms;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Room find(String title) {
		
		String query= "select * from room where title='"+title+"'";
		Connection connection= null;
		try {
			connection= database.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    Room room= new Room();
		    while (rs.next()) {
		    	room.setId(rs.getLong("id"));
		    	room.setTitle(rs.getString("title"));
		    	room.setOwner(userRepository.find(rs.getString("owner")));
		    	room.setCreationDate(rs.getDate("creationdate"));
		    	room.setPassword(rs.getString("password"));
		    	room.setMembers(new ArrayList<User>());
		    	room.setTags(new ArrayList<Tag>());
		    	RoomType type= new RoomType();
		    	type.setId(rs.getLong("type"));
		    	room.setType(type);
		    }
		    connection.close();
		    
	    	ArrayList<User> users= new ArrayList<User>();
	    	query= "select * from user_room where roomid='"+title+"'";
	    	connection= database.getDataSource().getConnection();
			stmt = connection.createStatement();
		    rs = stmt.executeQuery(query);
		    while(rs.next()) {
		    	users.add(userRepository.find(rs.getString("userid")));
		    }
		    room.setMembers(users);
		    connection.close();
		    
	    	ArrayList<Tag> tags= new ArrayList<Tag>();
	    	query= "select * from room_tag where roomid="+room.getId();
	    	connection= database.getDataSource().getConnection();
			stmt = connection.createStatement();
		    rs = stmt.executeQuery(query);
		    while(rs.next()) {
		    	tags.add(tagRepository.find(rs.getLong("tagid"))); 
		    }
		    room.setTags(tags);
		    connection.close();
		    
	    	query= "select * from roomtype where id="+room.getType().getId();
	    	connection= database.getDataSource().getConnection();
	    	stmt = connection.createStatement();
	    	rs = stmt.executeQuery(query);
	    	while(rs.next()) {
	    		room.getType().setRoomType(rs.getString("roomtype")); 
		    }
	    	
	    	connection.close();
		    
	    	
		    return room;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	//Just for public rooms
	@Override
	public String save(Room entity) {
		
		String query = "INSERT INTO room VALUES ("+entity.getId()+",'"+entity.getTitle()+"',"+entity.getType().getId()+",'"+entity.getOwner().getNickName()+"','"+entity.getCreationDate()+"','"+entity.getPassword()+"')";
		Connection connection = null;
		try {
			connection = database.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			stmt.execute(query);
			connection.close();
			return entity.getTitle();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void upadate(Room entity) {
		
		Room room= find(entity.getTitle());
		HashSet<String> old= new HashSet<String>();
		HashSet<String> newone= new HashSet<String>();
		for(User u: room.getMembers()) {
			old.add(u.getNickName());
		}
		for(User u: entity.getMembers()) {
			newone.add(u.getNickName());
		}
		newone.removeAll(old);
		System.out.println(newone.size());
		for(String s: newone) {
			String query = "insert into user_room values('"+s+"','"+entity.getTitle()+"')";
			Connection connection = null;
			try {
				connection = database.getDataSource().getConnection();
				Statement stmt = connection.createStatement();
				stmt.execute(query);
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void addUser(User user, String room) throws RoomException{
		String query= "select * from user_room where userid = '"+user.getNickName()+"' and roomid='"+room+"'";
		Connection connection= null;
		try {
			connection= database.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(!rs.next()){
				Room entity = find(room);
				entity.getMembers().add(user);
				upadate(entity);
			}
			else{
				throw new RoomException("Ya te encuentras en este grupo");
			}
			connection.close();
		}
		catch(RoomException e){
			throw e;
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new RoomException("Ocurrio un error al agregar el usuario a la sala");
			
		}
	}

	@Override
	public void delete(Room o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		
	}

}

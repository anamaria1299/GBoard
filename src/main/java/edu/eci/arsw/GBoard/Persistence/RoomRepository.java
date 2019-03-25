package edu.eci.arsw.GBoard.Persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.eci.arsw.GBoard.Persistence.Repositories.IRoomRepository;
import edu.eci.arsw.GBoard.config.DataBaseConfiguration;
import edu.eci.arsw.GBoard.model.Room;

@Component
public class RoomRepository implements IRoomRepository{
	
	@Autowired
	private DataBaseConfiguration database;

	@Override
	public List<Room> findAll() {
		String query= "select * from room";
		List<Room> rooms= new ArrayList<>();
		Connection connection= null;
		try {
			connection= database.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
		    ResultSet rs = stmt.executeQuery(query);;
		    while (rs.next()) {
		    	Room room= new Room();
		    	room.setId(rs.getLong("id"));
		    	room.setTitle(rs.getString("title"));
		    	//falta agregar los demas atributos
		    	
		    	rooms.add(room);
		    }
		    connection.close();
		    return rooms;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Room find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long save(Room entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void upadate(Room entity) {
		// TODO Auto-generated method stub
		
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

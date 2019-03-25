package edu.eci.arsw.GBoard.Persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.eci.arsw.GBoard.Persistence.Repositories.IRoomRepository;
import edu.eci.arsw.GBoard.config.DataBaseConfiguration;
import edu.eci.arsw.GBoard.model.Room;

public class RoomRepository implements IRoomRepository{
	
	@Autowired
	private DataBaseConfiguration database;

	@Override
	public List<Room> findAll() {
		// TODO Auto-generated method stub
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

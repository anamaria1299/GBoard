package edu.eci.arsw.GBoard.Persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.eci.arsw.GBoard.Persistence.Repositories.IRoomTypeRepository;
import edu.eci.arsw.GBoard.config.DataBaseConfiguration;
import edu.eci.arsw.GBoard.model.RoomType;

public class RoomTypeRepository implements IRoomTypeRepository{
	
	@Autowired
	private DataBaseConfiguration database;

	@Override
	public List<RoomType> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomType find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long save(RoomType entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void upadate(RoomType entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(RoomType o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		
	}

}

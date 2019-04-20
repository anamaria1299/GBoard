package edu.eci.arsw.GBoard.Persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.eci.arsw.GBoard.Persistence.Repositories.IRoomTypeRepository;
import edu.eci.arsw.GBoard.config.DataBaseConfiguration;
import edu.eci.arsw.GBoard.model.RoomType;

@Component
public class RoomTypeRepository implements IRoomTypeRepository{
	
	@Autowired
	private DataBaseConfiguration database;

	@Override
	public List<RoomType> findAll() {
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

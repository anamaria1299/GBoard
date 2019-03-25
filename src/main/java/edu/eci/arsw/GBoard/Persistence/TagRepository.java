package edu.eci.arsw.GBoard.Persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.eci.arsw.GBoard.Persistence.Repositories.ITagRepository;
import edu.eci.arsw.GBoard.config.DataBaseConfiguration;
import edu.eci.arsw.GBoard.model.Tag;

public class TagRepository implements ITagRepository{
	
	@Autowired
	private DataBaseConfiguration database;

	@Override
	public List<Tag> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tag find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long save(Tag entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void upadate(Tag entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Tag o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		
	}

}

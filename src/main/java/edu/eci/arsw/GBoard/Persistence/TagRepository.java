package edu.eci.arsw.GBoard.Persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.eci.arsw.GBoard.Persistence.Repositories.ITagRepository;
import edu.eci.arsw.GBoard.config.DataBaseConfiguration;
import edu.eci.arsw.GBoard.model.Tag;
import edu.eci.arsw.GBoard.model.User;

@Component
public class TagRepository implements ITagRepository{
	
	@Autowired
	private DataBaseConfiguration database;

	@Override
	public List<Tag> findAll() {
		String query= "select * from tag";
		List<Tag> tags= new ArrayList<>();
		Connection connection= null;
		try {
			connection= database.getDataSource().getConnection();
			Statement stmt = connection.createStatement();
		    ResultSet rs = stmt.executeQuery(query);;
		    while (rs.next()) {
		    	Tag tag= new Tag();
		    	tag.setId(rs.getLong("id"));
		    	tag.setTag(rs.getString("tag"));
		    	
		    	tags.add(tag);
		    }
		    connection.close();
		    return tags;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
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

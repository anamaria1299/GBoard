package edu.eci.arsw.GBoard.Persistence.Repositories;

import java.io.Serializable;
import java.util.List;

import edu.eci.arsw.GBoard.Persistence.UserException;

public interface DAO<T extends Serializable, PK> {
	
	public List<T> findAll();
	public T find(PK id);
	public PK save(T entity);
	public void upadate(T entity);
	public void delete(T o);
	public void remove(Long id);
}

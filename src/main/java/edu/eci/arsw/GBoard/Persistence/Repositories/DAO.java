package edu.eci.arsw.GBoard.Persistence.Repositories;

import java.io.Serializable;
import java.util.List;

import edu.eci.arsw.GBoard.Persistence.GBoardException;

public interface DAO<T extends Serializable, PK> {
	
	public List<T> findAll() throws GBoardException;
	public T find(PK id) throws GBoardException;
	public PK save(T entity) throws GBoardException;
	public void upadate(T entity) throws GBoardException;
	public void delete(T o);
	public void remove(Long id);
}

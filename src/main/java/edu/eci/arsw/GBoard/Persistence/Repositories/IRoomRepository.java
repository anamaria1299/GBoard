package edu.eci.arsw.GBoard.Persistence.Repositories;

import org.springframework.stereotype.Repository;

import edu.eci.arsw.GBoard.model.Room;

@Repository
public interface IRoomRepository extends DAO<Room, Long>{

}

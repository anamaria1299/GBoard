package edu.eci.arsw.GBoard.Persistence.Repositories;

import org.springframework.stereotype.Repository;

import edu.eci.arsw.GBoard.model.Tag;

@Repository
public interface ITagRepository extends DAO<Tag, Long>{

}

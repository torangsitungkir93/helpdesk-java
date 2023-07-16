package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.PriorityDao;
import com.lawencon.ticketing.model.Priority;


@Repository
@Profile("hql-query")
public class PriorityDaoHQLImpl implements PriorityDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Priority> getAll(){
		final String sql = "SELECT tp FROM Priority tp";
		
		final List<Priority> priorities = this.em.createQuery(sql,Priority.class).getResultList();
		return priorities;
	}

	@Override
	public Priority getByIdRef(Long id){
		final Priority priority = this.em.getReference(Priority.class, id);
		return priority;
	}
	
}

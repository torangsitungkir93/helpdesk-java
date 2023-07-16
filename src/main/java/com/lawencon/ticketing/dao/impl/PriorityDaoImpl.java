package com.lawencon.ticketing.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.PriorityDao;
import com.lawencon.ticketing.model.Priority;


@Repository
@Profile("native")
public class PriorityDaoImpl implements PriorityDao{
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Priority> getAll() {
		final String sql = "SELECT * FROM t_priority";
		
		final List<Priority> priorities = this.em.createNativeQuery(sql,Priority.class).getResultList();
		return priorities;
	}

	@Override
	public Priority getByIdRef(Long id) {
		final Priority priority = this.em.getReference(Priority.class, id);
		return priority;
	}
	
}

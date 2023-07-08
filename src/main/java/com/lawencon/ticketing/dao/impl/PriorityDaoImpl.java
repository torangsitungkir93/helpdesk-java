package com.lawencon.ticketing.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.PriorityDao;
import com.lawencon.ticketing.model.Priority;

public class PriorityDaoImpl implements PriorityDao{
	private final EntityManager em;
	
	public PriorityDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Priority> getAll() throws SQLException {
		final String sql = "SELECT * FROM t_priority";
		
		final List<Priority> priorities = this.em.createNativeQuery(sql,Priority.class).getResultList();
		return priorities;
	}

	@Override
	public Priority getByIdRef(Long id) throws SQLException {
		final Priority priority = this.em.getReference(Priority.class, id);
		return priority;
	}
	
}

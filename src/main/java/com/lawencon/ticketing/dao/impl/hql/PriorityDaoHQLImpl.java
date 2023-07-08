package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.PriorityDao;
import com.lawencon.ticketing.model.Priority;

public class PriorityDaoHQLImpl implements PriorityDao{
	private final EntityManager em;
	
	public PriorityDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public List<Priority> getAll() throws SQLException {
		final String sql = "SELECT tp FROM Priority tp";
		
		final List<Priority> priorities = this.em.createQuery(sql,Priority.class).getResultList();
		return priorities;
	}

	@Override
	public Priority getByIdRef(Long id) throws SQLException {
		final Priority priority = this.em.getReference(Priority.class, id);
		return priority;
	}
	
}

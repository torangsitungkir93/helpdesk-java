package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.TicketDeveloperDao;
import com.lawencon.ticketing.model.TicketDeveloper;

public class TicketDeveloperDaoHQLImpl implements TicketDeveloperDao{
	private final EntityManager em;

	public TicketDeveloperDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public List<TicketDeveloper> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TicketDeveloper insert(TicketDeveloper ticketDeveloper) throws SQLException {
		em.persist(ticketDeveloper);
		return ticketDeveloper;
	}

}

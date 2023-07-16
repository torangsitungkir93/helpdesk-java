package com.lawencon.ticketing.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.TicketDeveloperDao;
import com.lawencon.ticketing.model.TicketDeveloper;


@Repository
@Profile("native")
public class TicketDeveloperDaoImpl implements TicketDeveloperDao{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<TicketDeveloper> getAll() {
		return null;
	}

	@Override
	public TicketDeveloper insert(TicketDeveloper ticketDeveloper) {
		em.persist(ticketDeveloper);
		return ticketDeveloper;
	}

}

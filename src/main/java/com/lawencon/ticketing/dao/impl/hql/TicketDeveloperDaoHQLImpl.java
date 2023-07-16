package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.TicketDeveloperDao;
import com.lawencon.ticketing.model.TicketDeveloper;


@Repository
@Profile("hql-query")
public class TicketDeveloperDaoHQLImpl implements TicketDeveloperDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<TicketDeveloper> getAll(){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TicketDeveloper insert(TicketDeveloper ticketDeveloper){
		em.persist(ticketDeveloper);
		return ticketDeveloper;
	}

}

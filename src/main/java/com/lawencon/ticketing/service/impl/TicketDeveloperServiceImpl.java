package com.lawencon.ticketing.service.impl;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.lawencon.ticketing.dao.TicketDeveloperDao;
import com.lawencon.ticketing.model.Ticket;
import com.lawencon.ticketing.model.TicketDeveloper;
import com.lawencon.ticketing.model.User;
import com.lawencon.ticketing.service.TicketDeveloperService;


@Service
public class TicketDeveloperServiceImpl implements TicketDeveloperService{
	private TicketDeveloperDao ticketDeveloperDao;
	private TicketDeveloper ticketDeveloper = null;
	@PersistenceContext
	private EntityManager em;

	public TicketDeveloperServiceImpl(TicketDeveloperDao ticketDeveloperDao,DataSource dataSource,SessionFactory factory) {
		this.ticketDeveloperDao = ticketDeveloperDao;
	}

	@Override
	public TicketDeveloper insert(Long ticketId, Long devId, Long createdById) {
		try {
			this.em.getTransaction().begin();
			ticketDeveloper = new TicketDeveloper();
			
			final User user = new User();
			user.setId(devId);
			ticketDeveloper.setDeveloper(user);
			
			final Ticket ticket = new Ticket();
			ticket.setId(ticketId);
			ticketDeveloper.setTicket(ticket);
			ticketDeveloper.setCreatedBy(createdById);
			
			final LocalDateTime timeNow = LocalDateTime.now();
			ticketDeveloper.setCreatedAt(timeNow);
			ticketDeveloper.setIsActive(true);
			ticketDeveloper.setVer(0);

			ticketDeveloperDao.insert(ticketDeveloper);
			this.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.em.getTransaction().rollback();
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return ticketDeveloper;
	}
}

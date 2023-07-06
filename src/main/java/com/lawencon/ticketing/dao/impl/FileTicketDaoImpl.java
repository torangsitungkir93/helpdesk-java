package com.lawencon.ticketing.dao.impl;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.FileTicketDao;
import com.lawencon.ticketing.model.FileTicket;

public class FileTicketDaoImpl implements FileTicketDao{
	private final EntityManager em;
	
	public FileTicketDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public FileTicket insert(FileTicket fileTicket) throws SQLException {
		em.persist(fileTicket);
		 return fileTicket;
	}

}

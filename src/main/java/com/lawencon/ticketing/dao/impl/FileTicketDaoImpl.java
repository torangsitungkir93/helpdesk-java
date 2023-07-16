package com.lawencon.ticketing.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.FileTicketDao;
import com.lawencon.ticketing.model.FileTicket;


@Repository
@Profile("native")
public class FileTicketDaoImpl implements FileTicketDao{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public FileTicket insert(FileTicket fileTicket) {
		em.persist(fileTicket);
		 return fileTicket;
	}

}

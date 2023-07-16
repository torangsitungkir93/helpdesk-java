package com.lawencon.ticketing.dao.impl.hql;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.FileTicketDao;
import com.lawencon.ticketing.model.FileTicket;


@Repository
@Profile("hql-query")
public class FileTicketDaoHQLImpl implements FileTicketDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public FileTicket insert(FileTicket fileTicket){
		em.persist(fileTicket);
		 return fileTicket;
	}

}

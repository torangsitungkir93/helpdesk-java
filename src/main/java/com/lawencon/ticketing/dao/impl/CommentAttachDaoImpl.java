package com.lawencon.ticketing.dao.impl;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.CommentAttachDao;
import com.lawencon.ticketing.model.CommentAttach;


public class CommentAttachDaoImpl implements CommentAttachDao{
	private final EntityManager em;
	
	public CommentAttachDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public CommentAttach insert(CommentAttach commentAttach) throws SQLException {
		em.persist(commentAttach);
		return commentAttach;
	}


}

package com.lawencon.ticketing.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;

import com.lawencon.ticketing.config.EntityManagerConfig;
import com.lawencon.ticketing.dao.PriorityDao;
import com.lawencon.ticketing.model.Priority;
import com.lawencon.ticketing.service.PriorityService;

public class PriorityServiceImpl implements PriorityService{
	
	private final PriorityDao priorityDao;
	@SuppressWarnings("unused")
	private final EntityManager em;

	public PriorityServiceImpl(PriorityDao priorityDao,DataSource dataSource,SessionFactory factory) throws SQLException {
		this.priorityDao = priorityDao;
		this.em = EntityManagerConfig.get(factory);
	}

	@Override
	public List<Priority> getAll() throws SQLException {
		final List<Priority> priorities = priorityDao.getAll();
		return priorities;
	}

	@Override
	public Priority getByIdRef(Long id) throws SQLException {
		final Priority priority = priorityDao.getByIdRef(id);
		return priority;
	}
	
	
}

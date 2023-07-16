package com.lawencon.ticketing.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.lawencon.ticketing.dao.PriorityDao;
import com.lawencon.ticketing.model.Priority;
import com.lawencon.ticketing.service.PriorityService;

@Service
public class PriorityServiceImpl implements PriorityService{
	
	private final PriorityDao priorityDao;
	@PersistenceContext
	private EntityManager em;

	public PriorityServiceImpl(PriorityDao priorityDao,DataSource dataSource,SessionFactory factory) throws SQLException {
		this.priorityDao = priorityDao;
	}

	@Override
	public List<Priority> getAll() {
		final List<Priority> priorities = priorityDao.getAll();
		return priorities;
	}

	@Override
	public Priority getByIdRef(Long id) {
		final Priority priority = priorityDao.getByIdRef(id);
		return priority;
	}
	
	
}
